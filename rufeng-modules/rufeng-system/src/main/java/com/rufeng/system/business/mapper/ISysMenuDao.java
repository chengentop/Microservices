package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.business.domain.po.SysMenu;
import org.springframework.stereotype.Repository;

/**
 * SysMenuDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 20:12
 */
@Repository
public interface ISysMenuDao extends BaseMapper<SysMenu> {
}
