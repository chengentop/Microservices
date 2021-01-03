package com.rufeng.system.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.log.annotation.Log;
import com.rufeng.common.log.enums.BusinessType;
import com.rufeng.common.security.utils.SecurityUtils;
import com.rufeng.system.business.domain.po.SysMenu;
import com.rufeng.system.business.domain.vo.RouterVo;
import com.rufeng.system.business.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单权限控制器
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysmenuService;

    /**
     * 获取菜单权限
     *
     * @param menuid 菜单ID
     * @return R<SysMenu>
     */
    @RequestMapping(value = "/get/{menuid}", method = {RequestMethod.GET})
    public R<SysMenu> get(@PathVariable Integer menuid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysMenu menu = sysmenuService.get(menuid);
        return R.ok(menu);
    }

    /**
     * 查询菜单权限
     *
     * @param sysmenu 菜单权限对象
     * @return R<List < SysMenu>>
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public R<List<SysMenu>> list(SysMenu sysmenu) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysMenu> list = sysmenuService.list(sysmenu);
        return R.ok(list);
    }


    /**
     * 分页查询菜单权限
     *
     * @param page 分页对象
     * @param menu 菜单权限对象
     * @return R<IPage < SysMenu>>
     */
    @RequestMapping(value = "/list-by-page", method = {RequestMethod.GET})
    public R<IPage<SysMenu>> listPager(SysMenu menu, Pager page) throws Exception {
        IPage<SysMenu> menuIPage = sysmenuService.listPager(menu, page);
        return R.ok(menuIPage);
    }

    /**
     * 添加菜单权限
     *
     * @param menu 菜单权限对象
     * @return R<Boolean
     */
    @Log(title = "添加菜单权限", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public R<Boolean> add(@RequestBody SysMenu menu) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysmenuService.add(menu);
        return R.ok(flag);
    }

    /**
     * 更新菜单权限
     *
     * @param menu 菜单权限对象
     * @return R<Boolean
     */
    @Log(title = "更新菜单权限", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    public R<Boolean> update(@RequestBody SysMenu menu) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysmenuService.update(menu);
        return R.ok(flag);
    }

    /**
     * 删除菜单权限
     *
     * @param menuid 菜单ID
     * @return R<Boolean
     */
    @Log(title = "删除菜单权限", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/delete/{menuid}", method = {RequestMethod.DELETE})
    public R<Boolean> delete(@PathVariable Integer menuid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysmenuService.delete(menuid);
        return R.ok(flag);
    }

    /**
     * 根据用户查询 前端路由
     *
     * @return
     */
    @GetMapping("getRouters")
    public R<List<RouterVo>> getRouters() {
        Integer userId = SecurityUtils.getUserId();
        List<SysMenu> menus = sysmenuService.selectMenuTreeByUserId(userId);
        List<RouterVo> list = sysmenuService.buildMenus(menus);
        return R.ok(list);
    }

}