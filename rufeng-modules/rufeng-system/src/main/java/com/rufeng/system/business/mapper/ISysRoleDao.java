package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.api.domain.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SysRoleDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:31
 */
@Repository
public interface ISysRoleDao extends BaseMapper<SysRole> {
    /**
     * 根据用户id查询角色信息
     *
     * @param userid 用户id
     * @return List<SysRole>
     */
    public List<SysRole> selectPermissionByUserId(Integer userid);

    /**
     * 根据用户id查询角色信息
     *
     * @param roleid
     * @return
     */
    public SysRole selectRoleById(Integer roleid);
}
