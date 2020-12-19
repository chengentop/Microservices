package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.business.domain.po.SysPost;
import org.springframework.stereotype.Repository;

/**
 * SysPostDao 数据访问对象
 *
 * @auther chengen
 * @date 2020/12/18 18:41
 */
@Repository
public interface ISysPostDao extends BaseMapper<SysPost> {
}
