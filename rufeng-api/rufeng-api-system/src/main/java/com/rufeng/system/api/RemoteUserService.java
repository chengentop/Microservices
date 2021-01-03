package com.rufeng.system.api;

import com.rufeng.system.api.factory.RemoteUserFallbackFactory;
import com.rufeng.system.api.model.LoginUser;
import com.rufeng.common.core.constant.ServiceNameConstants;
import com.rufeng.common.core.web.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory =
        RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @RequestMapping(value = "user/info/{username}", method = RequestMethod.GET)
    public R<LoginUser> getUserInfo(@PathVariable("username") String username);
}
