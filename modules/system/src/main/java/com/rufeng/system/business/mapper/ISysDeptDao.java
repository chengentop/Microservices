package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.api.domain.SysDept;
import org.springframework.stereotype.Repository;

/**
 * SysDeptDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 19:13
 */
@Repository
public interface ISysDeptDao extends BaseMapper<SysDept> {
    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    public int updateDeptStatus(SysDept dept);
}
