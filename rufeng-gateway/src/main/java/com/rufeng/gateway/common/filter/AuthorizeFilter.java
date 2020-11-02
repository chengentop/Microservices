package com.rufeng.gateway.common.filter;

import com.rufeng.gateway.common.utils.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 定义全局过滤器 需要实现GlobalFilter和Order接口
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    /**
     * 执行过滤器业务逻辑
     * <p>
     * <p>
     * ServerWebExchange： 相当于请求和响应的上下文
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        // 排除不需要校验的路径
        if (path.contains("/login")) {
            return chain.filter(exchange); // 继续向下执行
        }
        // 获取令牌信息
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isNotEmpty(authorization)) { // 令牌不为空
            return chain.filter(exchange); // 继续向下执行
        } else { // 令牌为空
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // 请求结束
        }
    }

    /**
     * 指定过滤器执行顺序,返回值越小,优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
