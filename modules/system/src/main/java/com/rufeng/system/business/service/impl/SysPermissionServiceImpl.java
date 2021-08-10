package com.rufeng.system.business.service.impl;

import com.rufeng.system.api.domain.SysUser;
import com.rufeng.system.business.service.ISysMenuService;
import com.rufeng.system.business.service.ISysPermissionService;
import com.rufeng.system.business.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther chengen
 * @date 2020/12/31 14:38
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysMenuService menuService;

    @Override
    public Set<String> getRolePermission(Integer userId) {
        Set<String> roles = new HashSet<>();
        if (SysUser.isAdmin(userId))
            roles.add("admin");
        else
            roles.addAll(roleService.selecteRolePermissionByUserId(userId));
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(Integer userId) {
        Set<String> menus = new HashSet<>();
        if (SysUser.isAdmin(userId))
            menus.add("*:*:*");
        else
            menus.addAll(menuService.selectMenuPermissionByUserId(userId));
        return menus;
    }
}
