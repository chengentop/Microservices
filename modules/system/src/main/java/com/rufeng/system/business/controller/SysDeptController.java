package com.rufeng.system.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.common.core.web.controller.BaseController;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.log.annotation.Log;
import com.rufeng.common.log.enums.BusinessType;
import com.rufeng.common.security.annotation.PreAuthorize;
import com.rufeng.system.api.domain.SysDept;
import com.rufeng.system.business.domain.vo.TreeSelect;
import com.rufeng.system.business.service.ISysDeptService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;


/**
 * 部门控制器
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@RestController
@RequestMapping("dept")
public class SysDeptController extends BaseController {

    @Autowired
    private ISysDeptService sysdeptService;

    /**
     * 获取部门
     *
     * @param deptid 部门id
     * @return ResultData
     */
    @Log(title = "获取部门", businessType = BusinessType.SELECT)
    @RequestMapping(value = "/get/{deptid}", method = {RequestMethod.GET})
    public R<SysDept> get(@PathVariable Integer deptid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysDept sysDept = sysdeptService.get(deptid);
        return R.ok(sysDept);
    }

    /**
     * 查询部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public R<List<SysDept>> list(SysDept sysdept) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysDept> list = sysdeptService.list(sysdept);
        return R.ok(list);
    }


    /**
     * 分页查询部门
     *
     * @param page    分页对象
     * @param sysdept 部门对象
     * @return ResultData
     */
    @RequestMapping(value = "/list-by-page", method = {RequestMethod.GET})
    public R<IPage<SysDept>> listPager(SysDept sysdept, Pager page) throws Exception {
        IPage<SysDept> deptIPage = sysdeptService.listPager(sysdept, page);
        return R.ok(deptIPage);
    }

    /**
     * 添加部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     */
    @Log(title = "添加部门", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public R<Boolean> add(@RequestBody SysDept sysdept) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysdeptService.add(sysdept);
        return R.ok(flag);
    }

    /**
     * 更新部门
     *
     * @param sysdept 部门对象
     * @return ResultData
     */
    @Log(title = "更新部门", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    public R<Boolean> update(@RequestBody SysDept sysdept) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysdeptService.update(sysdept);
        return R.ok(flag);
    }

    /**
     * 删除部门
     *
     * @param deptid 部门id
     * @return ResultData
     */
    @Log(title = "删除部门", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/delete/{deptid}", method = {RequestMethod.DELETE})
    public R<Boolean> delete(@PathVariable Integer deptid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysdeptService.delete(deptid);
        return R.ok(flag);
    }


    /**
     * 查询部门
     *
     * @return ResultData
     */
    @RequestMapping(value = "/treeselect", method = {RequestMethod.GET})
    public R<List<TreeSelect>> treeselect(SysDept sysdept) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysDept> list = sysdeptService.list(sysdept);
        return R.ok(sysdeptService.buildDeptTreeSelect(list));
    }

    /**
     * 查询部门列表（排除节点）
     */
    @GetMapping("/list/exclude/{deptid}")
    public R<List<SysDept>> excludeChild(@PathVariable(value = "deptid", required = false) Integer deptid) throws Exception {
        List<SysDept> depts = sysdeptService.list(new SysDept());
        Iterator<SysDept> it = depts.iterator();
        while (it.hasNext()) {
            SysDept d = (SysDept) it.next();
            if (d.getDeptid().intValue() == deptid
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptid + "")) {
                it.remove();
            }
        }
        return R.ok(depts);
    }


}