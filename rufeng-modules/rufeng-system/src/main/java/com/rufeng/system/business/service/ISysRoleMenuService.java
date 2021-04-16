package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.api.domain.SysRole;
import com.rufeng.system.business.domain.po.SysRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * 角色与菜单关系 接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * 获取角色与菜单关系信息
     *
     * @param roleid 角色ID
     * @return SysRole
     * @throws Exception
     */
    public List<SysRoleMenu> getByRoleId(Integer roleid) throws Exception;

    /**
     * 查询角色与菜单关系信息
     *
     * @param roleMenu 角色信息对象
     * @return ResultData
     * @throws Exception
     */
    public List<SysRoleMenu> list(SysRoleMenu roleMenu);

    /**
     * 删除角色与菜单关系信息
     *
     * @param roleid 角色与菜单关系id
     * @return boolean
     * @throws Exception
     */
    public boolean deleteSysRole(Integer roleid) throws Exception;

}
