package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysDictData;

import java.util.List;


/**
 * 字典数据 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:13
 */
public interface ISysDictDataService extends IService<SysDictData> {

    /**
     * 获取字典数据
     *
     * @param dictcode 字典编码
     * @return SysDictData
     * @throws Exception
     */
    public SysDictData get(Integer dictcode) throws Exception;

    /**
     * 查询字典数据
     *
     * @param sysdictdata 字典数据对象
     * @return List<SysDictData>
     * @throws Exception
     */
    public List<SysDictData> list(SysDictData sysdictdata) throws Exception;

    /**
     * 分页查询字典数据
     *
     * @param sysdictdata 字典数据对象
     * @return IPage<SysDictData>
     * @throws Exception
     */
    public IPage<SysDictData> listPager(SysDictData sysdictdata, Pager page) throws Exception;

    /**
     * 添加字典数据
     *
     * @param sysdictdata 字典数据对象
     * @return boolean
     * @throws Exception
     */
    public boolean add(SysDictData sysdictdata) throws Exception;

    /**
     * 更新字典数据
     *
     * @param sysdictdata 字典数据对象
     * @return boolean
     * @throws Exception
     */
    public boolean update(SysDictData sysdictdata) throws Exception;

    /**
     * 删除字典数据
     *
     * @param dictcode 字典编码
     * @return boolean
     * @throws Exception
     */
    public boolean delete(Integer dictcode) throws Exception;

}