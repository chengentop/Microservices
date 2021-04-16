package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rufeng.system.api.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * SysUserDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:31
 */
@Repository
public interface ISysUserDao extends BaseMapper<SysUser> {
}