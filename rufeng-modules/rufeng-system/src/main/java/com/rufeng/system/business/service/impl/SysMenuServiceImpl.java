package com.rufeng.system.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.common.core.constant.UserConstants;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.common.security.utils.SecurityUtils;
import com.rufeng.system.business.domain.po.SysMenu;
import com.rufeng.system.business.domain.vo.MetaVo;
import com.rufeng.system.business.domain.vo.RouterVo;
import com.rufeng.system.business.mapper.ISysMenuDao;
import com.rufeng.system.business.service.ISysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 菜单权限 服务接口实现
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<ISysMenuDao, SysMenu> implements ISysMenuService {
    private Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private ISysMenuDao sysmenuDao;

    @Override
    public SysMenu get(Integer menuid) throws Exception {
        log.debug("###[服务] 开始查询菜单权限 menuid=[{}]. ", menuid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysMenu menu = sysmenuDao.selectById(menuid);
        log.info("###[服务] 完成查询菜单权限 menu=[{}]", menu);
        return menu;
    }

    @Override
    public List<SysMenu> list(SysMenu sysmenu) throws Exception {
        log.debug("###[服务] 开始根据条件{}查询菜单权限,menu=[{}]", sysmenu);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        QueryWrapper<SysMenu> sysmenuWrapper = new QueryWrapper<>();
        sysmenuWrapper.setEntity(sysmenu);
        List<SysMenu> menus = sysmenuDao.selectList(sysmenuWrapper);
        log.info("###[服务] 完成查询菜单权限 menus=[{}]", menus);
        return menus;
    }

    @Override
    public IPage<SysMenu> listPager(SysMenu sysmenu, Pager page) throws Exception {
        log.debug("###[服务] 开始根据条件{}分页查询菜单权限,sysmenu=[{}],page=[{}]", sysmenu, page);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Page<SysMenu> pageParam = new Page<>(page.getCurrentPage(), page.getTotalSize()); // 当前页码，每页条数
        QueryWrapper<SysMenu> sysmenuWrapper = new QueryWrapper<>();
        sysmenuWrapper.setEntity(sysmenu);
        IPage<SysMenu> pu = page(pageParam, sysmenuWrapper);
        log.info("###[服务] 完成分页查询菜单权限 pu=[{}]", pu);
        return pu;
    }

    @Override
    public boolean add(SysMenu menu) throws Exception {
        log.debug("###[服务] 开始添加新菜单权限, menu=[{}]", menu);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = save(menu);
        log.info("###[服务] 完成添加新菜单权限，flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean update(SysMenu menu) throws Exception {
        log.debug("###[服务] 开始根据条件{}修改菜单权限,menu=[{}]", menu);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = updateById(menu);
        log.info("###[服务] 完成修改菜单权限，flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean delete(Integer menuid) throws Exception {
        log.debug("###[服务] 开始根据条件{}删除菜单权限,menuid=[{}]", menuid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = removeById(menuid);
        log.info("###[服务] 完成删除菜单权限，flag=[{}]", flag);
        return flag;
    }

    @Override
    public Set<String> selectMenuPermissionByUserId(Integer userId) {
        log.debug("###[服务] 开始根据用户id查询菜单列表,userId=[{}]", userId);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Set<String> menus = new HashSet<>();
        List<String> list = sysmenuDao.selectMenuPermissionByUserId(userId);
        for (String str : list) {
            if (StringUtils.isEmpty(str))
                menus.addAll(Arrays.asList(str.trim().split(",")));

        }
        log.info("###[服务] 完成根据用户id查询菜单列表，menus=[{}]", menus);
        return menus;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Integer userId) {
        log.debug("###[服务] 开始根据用户ID查询菜单树信息,userId=[{}]", userId);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(userId)) {
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("distinct menuid, parentid, menuname, path, component, visible, status, ifnull(perms," +
                    "'') as perms, isframe, iscache, menutype, icon, ordernum, createtime")
                    .eq("status", '0')
                    .in("menutype", 'M', 'C')
                    .orderByAsc("parentid", "ordernum");
            menus = sysmenuDao.selectList(queryWrapper);
        } else {
            menus = sysmenuDao.selectMenuTreeByUserId(userId);
        }
        log.info("###[服务] 完成根据用户ID查询菜单树信息，menus=[{}]", menus);
        return getChildPerms(menus, 0);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuname(), menu.getIcon(), StringUtils.equals("1", menu.getIscache())));
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenutype())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuname(), menu.getIcon(), StringUtils.equals("1",
                        menu.getIscache())));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentid().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenutype())
                && UserConstants.NO_FRAME.equals(menu.getIsframe())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(SysMenu menu) {
        return menu.getParentid().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenutype())
                && menu.getIsframe().equals(UserConstants.NO_FRAME);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentid() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentid().longValue() == t.getMenuid().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
