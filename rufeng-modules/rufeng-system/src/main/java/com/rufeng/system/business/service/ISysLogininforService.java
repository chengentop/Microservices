package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysLogininfor;
import com.rufeng.system.business.domain.po.SysPost;

import java.util.List;

/**
 * 系统访问记录 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/19 14:20
 */
public interface ISysLogininforService extends IService<SysLogininfor> {

    /**
     * 获取岗位信息
     *
     * @param infoid 岗位ID
     * @return SysPost
     * @throws Exception
     */
    public SysLogininfor get(Integer infoid) throws Exception;

    /**
     * 查询岗位信息
     *
     * @param logininfor 岗位信息对象
     * @return List<SysLogininfor>
     * @throws Exception
     */
    public List<SysLogininfor> list(SysLogininfor logininfor) throws Exception;

    /**
     * 分页查询岗位信息
     *
     * @param logininfor 岗位信息对象
     * @return IPage<SysLogininfor>
     * @throws Exception
     */
    public IPage<SysLogininfor> listPager(SysLogininfor logininfor, Pager page) throws Exception;


    /**
     * 添加岗位信息
     *
     * @param logininfor 岗位信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean saveLogininfor(SysLogininfor logininfor) throws Exception;

    /**
     * 修改岗位信息
     *
     * @param logininfor 岗位信息对象
     * @return boolean
     * @throws Exception
     */
    public boolean updateLogininfor(SysLogininfor logininfor) throws Exception;

    /**
     * 删除岗位信息
     *
     * @param infoid 岗位id
     * @return boolean
     * @throws Exception
     */
    public boolean deleteLogininfor(Integer infoid) throws Exception;

    /**
     * 清空记录
     *
     * @return
     */
    public boolean cleanLogininfor();

}
