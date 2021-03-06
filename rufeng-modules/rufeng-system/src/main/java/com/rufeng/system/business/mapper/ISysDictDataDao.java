package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.business.domain.po.SysDictData;
import org.springframework.stereotype.Repository;

/**
 * SysDictDataDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:27
 */
@Repository
public interface ISysDictDataDao extends BaseMapper<SysDictData> {
}
