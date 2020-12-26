package com.rufeng.system.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.api.domain.SysOperLog;

import java.util.List;


/**
 * 操作日志记录 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/19 14:20F
 */
public interface ISysOperLogService extends IService<SysOperLog> {

    /**
     * 获取操作日志记录
     *
     * @param operid 日志主键
     * @return SysOperLog
     * @throws Exception
     */
    public SysOperLog get(Integer operid) throws Exception;

    /**
     * 查询操作日志记录
     *
     * @param operLog 操作日志记录对象
     * @return List<SysOperLog>
     * @throws Exception
     */
    public List<SysOperLog> list(SysOperLog operLog) throws Exception;

    /**
     * 分页查询操作日志记录
     *
     * @param operLog 操作日志记录对象
     * @return IPage<SysOperLog>
     * @throws Exception
     */
    public IPage<SysOperLog> listPager(SysOperLog operLog, Pager page) throws Exception;

    /**
     * 添加操作日志记录
     *
     * @param operLog 操作日志记录对象
     * @return boolean
     * @throws Exception
     */
    public boolean add(SysOperLog operLog) throws Exception;

    /**
     * 更新操作日志记录
     *
     * @param operLog 操作日志记录对象
     * @return boolean
     * @throws Exception
     */
    public boolean update(SysOperLog operLog) throws Exception;

    /**
     * 删除操作日志记录
     *
     * @param operid 日志主键
     * @return boolean
     * @throws Exception
     */
    public boolean delete(Integer operid) throws Exception;
}