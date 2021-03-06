package com.rufeng.system.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.web.controller.BaseController;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.security.utils.SecurityUtils;
import com.rufeng.system.api.domain.SysRole;
import com.rufeng.system.business.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息  控制器
 *
 * @auther chengen
 * @date 2020/12/18 18:22
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 根据用户id获取用户信息
     *
     * @param roleid 用户ID
     * @return ResultData
     */
    @RequestMapping(value = "/get/{roleid}", method = RequestMethod.GET)
    public R<SysRole> get(@PathVariable Integer roleid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysRole sysRole = sysRoleService.get(roleid);
        return R.ok(sysRole);
    }

    /**
     * 查询用户信息
     *
     * @param sysRole 用户信息对象
     * @return ResultData
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public R<List<SysRole>> list(SysRole sysRole) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysRole> list = sysRoleService.list(sysRole);
        return R.ok(list);
    }

    /**
     * 分页查询用户信息
     *
     * @param page    分页对象
     * @param sysRole 用户信息对象
     * @return ResultData
     */
    @RequestMapping(value = "/list-by-page", method = {RequestMethod.GET})
    public R<IPage<SysRole>> listPager(SysRole sysRole, Pager page) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        IPage<SysRole> sysUserIPage = sysRoleService.listPager(sysRole, page);
        return R.ok(sysUserIPage);
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public R<Boolean> saveSysUser(@RequestBody SysRole sysRole) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        sysRole.setCreateby(SecurityUtils.getUsername());
        boolean flag = sysRoleService.saveSysRole(sysRole);
        return R.ok(flag);
    }


    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    public R<Boolean> updateSysUser(@RequestBody SysRole sysRole) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysRoleService.updateSysRole(sysRole);
        return R.ok(flag);
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/delete/{roleid}", method = {RequestMethod.DELETE})
    public R<Boolean> deleteSysUser(@PathVariable Integer roleid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysRoleService.deleteSysRole(roleid);
        return R.ok(flag);
    }
}
