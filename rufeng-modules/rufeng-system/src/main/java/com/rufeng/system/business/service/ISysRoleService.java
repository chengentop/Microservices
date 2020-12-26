package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.api.domain.SysRole;

import java.util.List;

/**
 * 角色信息 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 获取角色信息
     *
     * @param roleid 角色ID
     * @return SysRole
     * @throws Exception
     */
    public SysRole get(Integer roleid) throws Exception;

    /**
     * 查询角色信息
     *
     * @param sysRole 角色信息对象
     * @return ResultData
     * @throws Exception
     */
    public List<SysRole> list(SysRole sysRole);

    /**
     * 分页查询角色信息
     *
     * @param sysRole 角色信息对象
     * @return ResultData
     * @throws Exception
     */
    public IPage<SysRole> listPager(SysRole sysRole, Pager page) throws Exception;

    /**
     * 添加角色信息
     *
     * @param sysRole 角色信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean saveSysRole(SysRole sysRole) throws Exception;

    /**
     * 修改角色信息
     *
     * @param sysRole 角色信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean updateSysRole(SysRole sysRole) throws Exception;

    /**
     * 删除角色信息
     *
     * @param roleid 角色id
     * @return boolean
     * @throws Exception
     */
    public boolean deleteSysRole(Integer roleid) throws Exception;
}
