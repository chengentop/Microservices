package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.business.domain.po.SysConfig;
import org.springframework.stereotype.Repository;

/**
 * SysConfigDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:13
 */
@Repository
public interface ISysConfigDao extends BaseMapper<SysConfig> {
}