package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysDictData;
import com.rufeng.system.business.domain.po.SysDictType;

import java.util.List;


/**
 * 字典类型 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:13
 */
public interface ISysDictTypeService extends IService<SysDictType> {

    /**
     * 获取字典类型
     *
     * @param dictid 字典主键
     * @return SysDictType
     * @throws Exception
     */
    public SysDictType get(Integer dictid) throws Exception;

    /**
     * 查询字典类型
     *
     * @param dictType 字典类型对象
     * @return List<SysDictType>
     * @throws Exception
     */
    public List<SysDictType> list(SysDictType dictType) throws Exception;

    /**
     * 分页查询字典类型
     *
     * @param dictType 字典类型对象
     * @return IPage<SysDictType>
     * @throws Exception
     */
    public IPage<SysDictType> listPager(SysDictType dictType, Pager page) throws Exception;

    /**
     * 添加字典类型
     *
     * @param dictType 字典类型对象
     * @return boolean
     * @throws Exception
     */
    public boolean add(SysDictType dictType) throws Exception;

    /**
     * 更新字典类型
     *
     * @param dictType 字典类型对象
     * @return boolean
     * @throws Exception
     */
    public boolean update(SysDictType dictType) throws Exception;

    /**
     * 删除字典类型
     *
     * @param dictid 字典主键
     * @return boolean
     * @throws Exception
     */
    public boolean delete(Integer dictid) throws Exception;

    /**
     * 根据字典类型获取字典类型
     *
     * @param dicttype 字典类型
     * @return SysDictType
     */
    public List<SysDictData> getByDictType(String dicttype);
}