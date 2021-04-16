package com.rufeng.system.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rufeng.system.business.domain.po.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SysMenuDao 数据访问对象
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/26 20:12
 */
@Repository
public interface ISysMenuDao extends BaseMapper<SysMenu> {
    /**
     * 根据用户id查询菜单
     *
     * @param userId
     * @return List<String>
     */
    public List<String> selectMenuPermissionByUserId(Integer userId);

    /**
     * 根据用户id查询菜单树
     *
     * @return List<SysMenu>
     */

    public List<SysMenu> selectMenuTreeByUserId(Integer userId);

    /**
     * 根据用户id查询菜单树
     *
     * @param queryWrapper
     * @return
     */
    public List<SysMenu> selectMenuListByUserId(QueryWrapper<SysMenu> queryWrapper);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleid
     * @param menucheckstrictly
     * @return
     */
    public List<Integer> selectMenuListByRoleId(@Param("roleid") Integer roleid, @Param("menucheckstrictly") Boolean menucheckstrictly);

}
