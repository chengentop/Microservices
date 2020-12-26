package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.api.domain.SysDept;

import java.util.List;


/**
 * 部门 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:13
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 获取部门
     *
     * @param deptid 部门id
     * @return ResultData
     * @throws Exception
     */
    public SysDept get(Integer deptid) throws Exception;

    /**
     * 查询部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     * @throws Exception
     */
    public List<SysDept> list(SysDept sysdept) throws Exception;

    /**
     * 分页查询部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     * @throws Exception
     */
    public IPage<SysDept> listPager(SysDept sysdept, Pager page) throws Exception;

    /**
     * 添加部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     * @throws Exception
     */
    public boolean add(SysDept sysdept) throws Exception;

    /**
     * 更新部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     * @throws Exception
     */
    public boolean update(SysDept sysdept) throws Exception;

    /**
     * 删除部门
     *
     * @param deptid 部门id
     * @return ResultData
     * @throws Exception
     */
    public boolean delete(Integer deptid) throws Exception;


}