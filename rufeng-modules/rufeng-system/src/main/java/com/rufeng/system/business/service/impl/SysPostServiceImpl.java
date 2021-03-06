package com.rufeng.system.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysPost;
import com.rufeng.system.business.mapper.ISysPostDao;
import com.rufeng.system.business.service.ISysPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位信息 服务接口实现
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<ISysPostDao, SysPost> implements ISysPostService {
    private Logger log = LoggerFactory.getLogger(SysPostServiceImpl.class);
    @Autowired
    private ISysPostDao sysPostDao;

    @Override
    public SysPost get(Integer postid) throws Exception {
        log.debug("###[服务] 开始查询岗位信息 postid=[{}]. ", postid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysPost sysPost = getById(postid);
        log.info("###[服务] 完成查询岗位信息 sysPost=[{}]", sysPost);
        return sysPost;
    }

    @Override
    public List<SysPost> list(SysPost sysPost) {
        log.debug("###[服务] 开始根据条件{}查询岗位信息,sysPost=[{}]", sysPost);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        QueryWrapper<SysPost> queryWrapper = new QueryWrapper<>();
        List<SysPost> list = list(queryWrapper);
        log.info("###[服务] 完成分页查询岗位信息 list=[{}]", list);
        return list;
    }

    @Override
    public IPage<SysPost> listPager(SysPost sysPost, Pager page) throws Exception {
        log.debug("###[服务] 开始根据条件分页查询岗位信息,sysPost=[{}],page=[{}]", sysPost, page);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Page<SysPost> pageParam = new Page<>(page.getCurrentPage(), page.getTotalSize()); // 当前页码，每页条数
        QueryWrapper<SysPost> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(sysPost);
        IPage<SysPost> pu = page(pageParam, userQueryWrapper);
        log.info("###[服务] 完成分页查询岗位信息 pu=[{}]", pu);
        return pu;
    }

    @Override
    public boolean saveSysPost(SysPost sysPost) throws Exception {
        log.debug("###[服务] 开始根据岗位id删除岗位信息 sysPost=[{}]", sysPost);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = save(sysPost);
        log.info("###[服务] 完成根据岗位id删除岗位信息 flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean updateSysPost(SysPost sysPost) throws Exception {
        log.debug("###[服务] 开始根据岗位id{}更新岗位信息 sysPost=[{}]", sysPost.getPostid(), sysPost);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = updateById(sysPost);
        log.info("###[服务] 完成根据岗位id更新岗位信息 flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean deleteSysPost(Integer postid) throws Exception {
        log.debug("###[服务] 开始根据岗位id删除岗位信息 postid=[{}]", postid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = removeById(postid);
        log.info("###[服务] 完成根据岗位id删除岗位信息 flag=[{}]", flag);
        return flag;
    }
}
