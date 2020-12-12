package com.rufeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.api.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * SysUserDao 数据访问对象
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@Repository
public interface ISysUserDao extends BaseMapper<SysUser> {
}