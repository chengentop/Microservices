package com.rufeng.auth.controller;

import com.rufeng.auth.from.LoginBody;
import com.rufeng.auth.service.SysLoginService;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.security.service.TokenService;
import com.rufeng.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther chengen
 * @date 2020/11/26 11:20
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysLoginService sysLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R<?> login(@RequestBody LoginBody loginBody) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

}
