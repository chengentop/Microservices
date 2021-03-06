package com.rufeng.system.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.api.domain.SysUser;
import com.rufeng.system.business.mapper.ISysUserDao;
import com.rufeng.system.business.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户信息 服务接口实现
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserDao, SysUser> implements ISysUserService {
    private Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private ISysUserDao sysuserDao;


    @Override
    public SysUser get(Integer userid) throws Exception {
        log.debug("###[服务] 开始查询用户信息 userid=[{}]. ", userid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysUser sysuser = getById(userid);
        log.info("###[服务] 完成查询用户信息 sysUserVo=[{}]", sysuser);
        return sysuser;
    }

    @Override
    public List<SysUser> list(SysUser sysuser) {
        log.debug("###[服务] 开始根据条件{}查询用户信息,sysuser=[{}]", sysuser);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        List<SysUser> list = list(queryWrapper);
        log.info("###[服务] 完成分页查询用户信息 list=[{}]", list);
        return list;
    }

    @Override
    public IPage<SysUser> listPager(SysUser sysuser, Pager page) throws Exception {
        log.debug("###[服务] 开始根据条件分页查询用户信息,sysuser=[{}],page=[{}]", sysuser, page);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Page<SysUser> pageParam = new Page<>(page.getCurrentPage(), page.getTotalSize()); // 当前页码，每页条数
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(sysuser);
        IPage<SysUser> pu = page(pageParam, userQueryWrapper);
        log.info("###[服务] 完成分页查询用户信息 pu=[{}]", pu);
        return pu;
    }

    @Override
    public boolean saveSysUser(SysUser sysuser) throws Exception {
        log.debug("###[服务] 开始根据用户id删除用户信息 sysuser=[{}]", sysuser);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = save(sysuser);
        log.info("###[服务] 完成根据用户id删除用户信息 flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean updateSysUser(SysUser sysuser) throws Exception {
        log.debug("###[服务] 开始根据用户id{}更新用户信息 userid=[{}]", sysuser.getUserid(), sysuser);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = updateById(sysuser);
        log.info("###[服务] 完成根据用户id更新用户信息 flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean deleteSysUser(Integer userid) throws Exception {
        log.debug("###[服务] 开始根据用户id删除用户信息 userid=[{}]", userid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = removeById(userid);
        log.info("###[服务] 完成根据用户id删除用户信息 flag=[{}]", flag);
        return flag;
    }

    @Override
    public SysUser getByUsername(String username) {
        log.debug("###[服务] 开始查询用户信息 username=[{}]. ", username);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysUser sysuser = sysuserDao.selectOne(queryWrapper);
        log.info("###[服务] 完成查询用户信息 sysuser=[{}]", sysuser);
        return sysuser;
    }

    @Override
    public boolean changeStatus(Integer userid, String status) {
        log.debug("###[服务] 开始查询用户id修改用户状态 userId=[{}]. ", userid);
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userid).set("status", status);
        boolean flag = update(updateWrapper);
        log.info("###[服务] 完成根据用户id修改用户状态 flag=[{}]", flag);
        return flag;
    }

}
