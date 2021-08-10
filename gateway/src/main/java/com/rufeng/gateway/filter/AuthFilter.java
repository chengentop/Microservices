package com.rufeng.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rufeng.common.core.constant.CacheConstants;
import com.rufeng.common.core.constant.Constants;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.common.redis.service.RedisService;
import com.rufeng.gateway.config.properties.WhiteListProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @auther chengen
 * @date 2020/11/26 18:18
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private RedisService redisService;

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private WhiteListProperties whiteList;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> sops;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求地址
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, whiteList.getWhites())) {
            return chain.filter(exchange);
        }
        // 获取token
        String token = getToken(exchange.getRequest());

        if (StringUtils.isBlank(token)) {
            return setUnauthorizedResponse(exchange, "Token不能为空",Constants.FAIL);
        }
        String userStr = sops.get(getTokenKey(token));
        if (StringUtils.isNull(userStr)) {
            return setUnauthorizedResponse(exchange, "登录状态已过期",Constants.TOKEN_PAST_DUS);
        }
        JSONObject obj = JSONObject.parseObject(userStr);
        String userid = obj.getString("userid");
        String username = obj.getString("username");
        if (StringUtils.isBlank(userid) || StringUtils.isBlank(username)) {
            return setUnauthorizedResponse(exchange, "令牌验证失败",Constants.FAIL);
        }
        // 设置过期时间
        redisService.expire(getTokenKey(token), EXPIRE_TIME);
        // 设置用户信息到请求
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CacheConstants.DETAILS_USER_ID, userid)
                .header(CacheConstants.DETAILS_USERNAME, username).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();

        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(CacheConstants.HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX)) {
            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg,int code ) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(R.fail(code,msg)));
        }));
    }
}
