package com.rufeng.system.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.web.controller.BaseController;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.security.utils.SecurityUtils;
import com.rufeng.system.api.domain.SysUser;
import com.rufeng.system.api.model.LoginUser;
import com.rufeng.system.business.service.ISysPermissionService;
import com.rufeng.system.business.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 用户信息  控制器
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@RestController
@RequestMapping("user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService sysuserService;
    @Autowired
    private ISysPermissionService permissionService;

    /**
     * 根据用户id获取用户信息
     *
     * @param userid 用户ID
     * @return ResultData
     */
    @RequestMapping(value = "/get/{userid}", method = RequestMethod.GET)
    public R<LoginUser> get(@PathVariable Integer userid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysUser sysUser = sysuserService.get(userid);
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setUser(sysUser);
        return R.ok(sysUserVo);
    }

    /**
     * 查询用户信息
     *
     * @param sysuser 用户信息对象
     * @return ResultData
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public R<List<SysUser>> list(SysUser sysuser) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysUser> list = sysuserService.list(sysuser);
        return R.ok(list);
    }

    /**
     * 分页查询用户信息
     *
     * @param page    分页对象
     * @param sysuser 用户信息对象
     * @return ResultData
     */
    @RequestMapping(value = "/list-by-page", method = {RequestMethod.GET})
    public R<IPage<SysUser>> listPager(SysUser sysuser, Pager page) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        IPage<SysUser> sysUserIPage = sysuserService.listPager(sysuser, page);
        return R.ok(sysUserIPage);
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public R<Boolean> saveSysUser(@RequestBody SysUser sysuser) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysuserService.saveSysUser(sysuser);
        return R.ok(flag);
    }


    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    public R<Boolean> updateSysUser(@RequestBody SysUser sysuser) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysuserService.updateSysUser(sysuser);
        return R.ok(flag);
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/delete/{userid}", method = {RequestMethod.DELETE})
    public R<Boolean> deleteSysUser(@PathVariable Integer userid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysuserService.deleteSysUser(userid);
        return R.ok(flag);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名称
     * @return ResultData
     */
    @RequestMapping(value = "/info/{username}", method = RequestMethod.GET)
    public R<LoginUser> info(@PathVariable String username) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysUser sysUser = sysuserService.getByUsername(username);
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setUser(sysUser);
        return R.ok(sysUserVo);
    }

    /**
     * 获取用户信息
     *
     * @return ResultData
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public R<Map<String, Object>> getinfo() throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Map<String, Object> map = new HashMap<>();
        Integer userId = SecurityUtils.getUserId();
        SysUser user = sysuserService.get(userId);
        map.put("user", user);

        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        map.put("roles", roles);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        map.put("permissions", permissions);

        return R.ok(map);
    }


}