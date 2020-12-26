package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.api.domain.SysRole;
import org.springframework.stereotype.Repository;

/**
 * SysRoleDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:31
 */
@Repository
public interface ISysRoleDao extends BaseMapper<SysRole> {
}
