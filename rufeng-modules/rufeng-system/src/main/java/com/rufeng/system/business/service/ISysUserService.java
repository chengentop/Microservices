package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.api.domain.SysUser;

import java.util.List;


/**
 * 用户信息 服务接口
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 获取用户信息
     *
     * @param userid 用户ID
     * @return SysUser
     * @throws Exception
     */
    public SysUser get(Integer userid) throws Exception;

    /**
     * 查询用户信息
     *
     * @param sysuser 用户信息对象
     * @return ResultData
     * @throws Exception
     */
    public List<SysUser> list(SysUser sysuser);

    /**
     * 分页查询用户信息
     *
     * @param sysuser 用户信息对象
     * @return ResultData
     * @throws Exception
     */
    public IPage<SysUser> listPager(SysUser sysuser, Pager page) throws Exception;

    /**
     * 添加用户信息
     *
     * @param sysuser 用户信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean saveSysUser(SysUser sysuser) throws Exception;

    /**
     * 修改用户信息
     *
     * @param sysuser 用户信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean updateSysUser(SysUser sysuser) throws Exception;

    /**
     * 删除用户信息
     *
     * @param userid 用户id
     * @return boolean
     * @throws Exception
     */
    public boolean deleteSysUser(Integer userid) throws Exception;


}