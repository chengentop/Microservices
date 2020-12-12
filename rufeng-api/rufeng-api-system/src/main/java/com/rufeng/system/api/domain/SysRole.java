package com.rufeng.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * sysrole 数据持久化对象
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long roleid;  //角色ID
    private String rolename;  //角色名称
    private String rolekey;  //角色权限字符串
    private Integer rolesort;  //显示顺序
    private String datascope;  //数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
    private String status;  //角色状态（0正常 1停用）
    private String delflag;  //删除标志（0代表存在 2代表删除）
    private String createby;  //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private Timestamp createtime;  //创建时间
    private String updateby;  //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private Timestamp updatetime;  //更新时间
    private String remark;  //备注
    private Boolean menucheckstrictly;// 菜单树选择项是否关联显示
    /**
     * 菜单组
     */
    @TableField(exist = false)
    private Long[] menuIds;


    public Long getRoleid() {
        return this.roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolekey() {
        return this.rolekey;
    }

    public void setRolekey(String rolekey) {
        this.rolekey = rolekey;
    }

    public Integer getRolesort() {
        return this.rolesort;
    }

    public void setRolesort(Integer rolesort) {
        this.rolesort = rolesort;
    }

    public String getDatascope() {
        return this.datascope;
    }

    public void setDatascope(String datascope) {
        this.datascope = datascope;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelflag() {
        return this.delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getCreateby() {
        return this.createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return this.updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Timestamp getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isMenucheckstrictly() {
        return menucheckstrictly;
    }

    public void setMenucheckstrictly(Boolean menucheckstrictly) {
        this.menucheckstrictly = menucheckstrictly;
    }

    public boolean isAdmin() {
        return isAdmin(this.roleid);
    }

    public static boolean isAdmin(Long roleid) {
        return roleid != null && 1L == roleid;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("com.rufeng: ");
        if (null != roleid) sb.append("roleid=").append(roleid).append(",");
        if (null != rolename) sb.append("rolename=").append(rolename).append(",");
        if (null != rolekey) sb.append("rolekey=").append(rolekey).append(",");
        if (null != rolesort) sb.append("rolesort=").append(rolesort).append(",");
        if (null != datascope) sb.append("datascope=").append(datascope).append(",");
        if (null != status) sb.append("status=").append(status).append(",");
        if (null != delflag) sb.append("delflag=").append(delflag).append(",");
        if (null != createby) sb.append("createby=").append(createby).append(",");
        if (null != createtime) sb.append("createtime=").append(createtime).append(",");
        if (null != updateby) sb.append("updateby=").append(updateby).append(",");
        if (null != updatetime) sb.append("updatetime=").append(updatetime).append(",");
        if (null != remark) sb.append("remark=").append(remark).append(",");
        if (menucheckstrictly) sb.append("menucheckstrictly=").append(menucheckstrictly).append(",");
        return sb.toString();
    }
}