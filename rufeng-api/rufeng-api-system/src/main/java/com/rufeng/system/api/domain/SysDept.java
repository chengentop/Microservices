package com.rufeng.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * sysdept 数据持久化对象
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@TableName("sys_dept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long deptid;  //部门id
    private Long parentid;  //父部门id
    private String ancestors;  //祖级列表
    private String deptname;  //部门名称
    private String ordernum;  //显示顺序
    private String leader;  //负责人
    private String phone;  //联系电话
    private String email;  //邮箱
    private String status;  //部门状态（0正常 1停用）
    private String delflag;  //删除标志（0代表存在 2代表删除）
    /**
     * 父部门名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 子部门
     */
    @TableField(exist = false)
    private List<SysDept> children = new ArrayList<SysDept>();

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren() {
        return children;
    }

    public void setChildren(List<SysDept> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("com.rufeng: ");
        if (null != deptid) sb.append("deptid=").append(deptid).append(",");
        if (null != parentid) sb.append("parentid=").append(parentid).append(",");
        if (null != ancestors) sb.append("ancestors=").append(ancestors).append(",");
        if (null != deptname) sb.append("deptname=").append(deptname).append(",");
        if (null != ordernum) sb.append("ordernum=").append(ordernum).append(",");
        if (null != leader) sb.append("leader=").append(leader).append(",");
        if (null != phone) sb.append("phone=").append(phone).append(",");
        if (null != email) sb.append("email=").append(email).append(",");
        if (null != status) sb.append("status=").append(status).append(",");
        if (null != delflag) sb.append("delflag=").append(delflag).append(",");
        return sb.toString();
    }
}