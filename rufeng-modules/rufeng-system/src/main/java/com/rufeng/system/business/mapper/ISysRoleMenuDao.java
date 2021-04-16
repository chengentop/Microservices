package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.business.domain.po.SysRoleMenu;
import org.springframework.stereotype.Repository;

/**
 * SysRoleMenuDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 20:00
 */

@Repository
public interface ISysRoleMenuDao extends BaseMapper<SysRoleMenu> {
}
