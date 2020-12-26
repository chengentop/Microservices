package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysConfig;

import java.util.List;

/**
 * 参数配置 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:13
 */
public interface ISysConfigService extends IService<SysConfig> {

    /**
     * 获取参数配置
     *
     * @param configid 参数主键
     * @return SysConfig
     * @throws Exception
     */
    public SysConfig get(String configid) throws Exception;

    /**
     * 查询参数配置
     *
     * @param sysconfig 参数配置对象
     * @return List<SysConfig>
     * @throws Exception
     */
    public List<SysConfig> list(SysConfig sysconfig) throws Exception;

    /**
     * 分页查询参数配置
     *
     * @param sysconfig 参数配置对象
     * @return IPage<SysConfig>
     * @throws Exception
     */
    public IPage<SysConfig> listPager(SysConfig sysconfig, Pager page) throws Exception;

    /**
     * 添加参数配置
     *
     * @param sysconfig 参数配置对象
     * @return boolean
     * @throws Exception
     */
    public boolean add(SysConfig sysconfig) throws Exception;

    /**
     * 更新参数配置
     *
     * @param sysconfig 参数配置对象
     * @return boolean
     * @throws Exception
     */
    public boolean update(SysConfig sysconfig) throws Exception;

    /**
     * 删除参数配置
     *
     * @param config_id 参数主键
     * @return boolean
     * @throws Exception
     */
    public boolean delete(Integer config_id) throws Exception;


}