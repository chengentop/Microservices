package com.rufeng.system.business.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysMenu;

import java.util.List;


/**
 * 菜单权限 服务接口
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/19 14:20
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单权限
     *
     * @param menuid 菜单ID
     * @return SysMenu
     * @throws Exception
     */
    public SysMenu get(Integer menuid) throws Exception;

    /**
     * 查询菜单权限
     *
     * @param menu 菜单权限对象
     * @return List<SysMenu>
     * @throws Exception
     */
    public List<SysMenu> list(SysMenu menu) throws Exception;

    /**
     * 分页查询菜单权限
     *
     * @param menu 菜单权限对象
     * @return IPage<SysMenu>
     * @throws Exception
     */
    public IPage<SysMenu> listPager(SysMenu menu, Pager page) throws Exception;

    /**
     * 添加菜单权限
     *
     * @param menu 菜单权限对象
     * @return boolean
     * @throws Exception
     */
    public boolean add(SysMenu menu) throws Exception;

    /**
     * 更新菜单权限
     *
     * @param menu 菜单权限对象
     * @return boolean
     * @throws Exception
     */
    public boolean update(SysMenu menu) throws Exception;

    /**
     * 删除菜单权限
     *
     * @param menuid 菜单ID
     * @return boolean
     * @throws Exception
     */
    public boolean delete(Integer menuid) throws Exception;

}