package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.api.domain.SysOperLog;
import org.springframework.stereotype.Repository;

/**
 * SysOperLogDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 20:26
 */
@Repository
public interface ISysOperLogDao extends BaseMapper<SysOperLog> {
}
