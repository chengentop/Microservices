package com.rufeng.system.business.service;

import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.core.web.domain.ResultData;
import com.rufeng.system.api.domain.SysUser;
import com.rufeng.system.api.model.LoginUser;


/**
 * 用户信息 服务接口
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
public interface ISysUserService {

    /**
     * 获取用户信息
     *
     * @param user_id 用户ID
     * @return ResultData
     * @throws Exception
     */
    public R<LoginUser> get(String user_id) throws Exception;

    /**
     * 分页查询用户信息
     *
     * @param sysuser 用户信息对象
     * @return ResultData
     * @throws Exception
     */
    public ResultData listPager(SysUser sysuser, Pager page) throws Exception;
}