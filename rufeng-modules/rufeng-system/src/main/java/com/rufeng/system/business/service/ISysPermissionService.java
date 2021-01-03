package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface ISysPermissionService {
    /**
     * 获取角色数据权限
     *
     * @param userId 用户Id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(Integer userId);

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(Integer userId);
}
