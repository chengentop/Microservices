package com.rufeng.system.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.common.core.constant.UserConstants;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.system.api.domain.SysDept;
import com.rufeng.system.business.domain.vo.TreeSelect;
import com.rufeng.system.business.mapper.ISysDeptDao;
import com.rufeng.system.business.service.ISysDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.PrintConversionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 部门 服务接口实现
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<ISysDeptDao, SysDept> implements ISysDeptService {
    private Logger log = LoggerFactory.getLogger(SysDeptServiceImpl.class);

    @Autowired
    private ISysDeptDao sysdeptDao;

    @Override
    public SysDept get(Integer deptid) throws Exception {
        log.debug("###[服务] 开始查询部门 deptid=[{}]. ", deptid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysDept sysdept = getById(deptid);
        log.info("###[服务] 完成查询部门 sysdept=[{}]", sysdept);
        return sysdept;
    }

    @Override
    public List<SysDept> list(SysDept sysdept) throws Exception {
        log.debug("###[服务] 开始根据条件{}查询部门,sysdept=[{}]", sysdept);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        QueryWrapper<SysDept> sysdeptWrapper = new QueryWrapper<>();
        sysdeptWrapper.setEntity(sysdept);
        List<SysDept> sysdepts = list(sysdeptWrapper);
        log.info("###[服务] 完成查询部门 sysdepts=[{}]", sysdepts);
        return sysdepts;
    }

    @Override
    public IPage<SysDept> listPager(SysDept sysdept, Pager page) throws Exception {
        log.debug("###[服务] 开始根据条件{}分页查询部门,sysdept=[{}]", sysdept);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Page<SysDept> pageParam = new Page<>(page.getCurrentPage(), page.getTotalSize()); // 当前页码，每页条数
        QueryWrapper<SysDept> sysdeptWrapper = new QueryWrapper<>();
        sysdeptWrapper.setEntity(sysdept);
        IPage<SysDept> pu = page(pageParam, sysdeptWrapper);
        log.info("###[服务] 完成分页查询部门 pu=[{}]", pu);
        return pu;
    }

    @Override
    public boolean add(SysDept sysdept) throws Exception {
        log.debug("###[服务] 开始添加新部门,sysdept=[{}]", sysdept);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = save(sysdept);
        log.info("###[服务] 完成添加新部门，flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean update(SysDept sysdept) throws Exception {
        log.debug("###[服务] 开始根据条件{}修改部门,", sysdept);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysDept newdept = getById(sysdept.getParentid());
        SysDept olddept = getById(sysdept.getDeptid());

        if (StringUtils.isNotNull(newdept) && StringUtils.isNotNull(olddept)) {
            String newAncestors = newdept.getAncestors() + "," + newdept.getDeptid();
            String oldAncestors = olddept.getAncestors();
            sysdept.setAncestors(newAncestors);
            updateDeptChildren(sysdept.getDeptid(), newAncestors, oldAncestors);
        }
        boolean flag = updateById(sysdept);
        if (UserConstants.DEPT_NORMAL.equals(sysdept.getStatus())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(sysdept);
        }
        log.info("###[服务] 完成修改部门，flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean delete(Integer deptid) throws Exception {
        log.debug("###[服务] 开始根据条件{}删除部门,deptid=[{}]", deptid);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = removeById(deptid);
        log.info("###[服务] 完成删除部门，flag=[{}]", flag);
        return flag;
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> list) {
        List<SysDept> deptTrees = buildDeptTree(list);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        List<SysDept> returnList = new ArrayList<SysDept>();
        List<Integer> tempList = new ArrayList<>();
        for (SysDept dept : depts) {
            tempList.add(dept.getDeptid());
        }
        for (Iterator<SysDept> iterator = depts.iterator(); iterator.hasNext(); ) {
            SysDept dept = (SysDept) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentid())) {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()) {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<SysDept>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext()) {
            SysDept n = (SysDept) it.next();
            if (StringUtils.isNotNull(n.getParentid()) && n.getParentid().intValue() == t.getDeptid().intValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 修改子元素关系
     *
     * @param deptid       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Integer deptid, String newAncestors, String oldAncestors) {
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        wrapper.apply(deptid != null, "FIND_IN_SET (" + deptid + ",ancestors)");
        List<SysDept> children = sysdeptDao.selectList(wrapper);
        for (SysDept child : children) {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            updateBatchById(children);
        }
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept) {
        dept = sysdeptDao.selectById(dept.getDeptid());
        sysdeptDao.updateDeptStatus(dept);
    }

}

