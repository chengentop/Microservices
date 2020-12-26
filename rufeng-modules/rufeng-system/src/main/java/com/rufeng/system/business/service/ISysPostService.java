package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysPost;

import java.util.List;

/**
 * 岗位信息 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:42
 */
public interface ISysPostService extends IService<SysPost> {

    /**
     * 获取岗位信息
     *
     * @param postid 岗位ID
     * @return SysPost
     * @throws Exception
     */
    public SysPost get(Integer postid) throws Exception;

    /**
     * 查询岗位信息
     *
     * @param sysPost 岗位信息对象
     * @return List<SysPost>
     * @throws Exception
     */
    public List<SysPost> list(SysPost sysPost);

    /**
     * 分页查询岗位信息
     *
     * @param sysPost 岗位信息对象
     * @return IPage<SysPost>
     * @throws Exception
     */
    public IPage<SysPost> listPager(SysPost sysPost, Pager page) throws Exception;

    /**
     * 添加岗位信息
     *
     * @param sysPost 岗位信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean saveSysPost(SysPost sysPost) throws Exception;

    /**
     * 修改岗位信息
     *
     * @param sysPost 岗位信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean updateSysPost(SysPost sysPost) throws Exception;

    /**
     * 删除岗位信息
     *
     * @param postid 岗位id
     * @return boolean
     * @throws Exception
     */
    public boolean deleteSysPost(Integer postid) throws Exception;
}
