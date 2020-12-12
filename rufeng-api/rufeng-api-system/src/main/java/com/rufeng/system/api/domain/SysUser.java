package com.rufeng.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * sys_user 数据持久化对象
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@TableName("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long userid;  //用户ID
    private Long deptid;  //部门ID
    private String username;  //用户账号
    private String nickname;  //用户昵称
    private String usertype;  //用户类型（00系统用户）
    private String email;  //用户邮箱
    private String phonenumber;  //手机号码
    private String sex;  //用户性别（0男 1女 2未知）
    private String avatar;  //头像地址
    private String password;  //密码
    private String status;  //帐号状态（0正常 1停用）
    private String delflag;  //删除标志（0代表存在 2代表删除）
    private String loginip;  //最后登陆IP
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private Timestamp logindate;  //最后登陆时间
    private String createby;  //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private Timestamp createtime;  //创建时间
    private String updateby;  //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private Timestamp updatetime;  //更新时间
    private String remark;  //备注
    @TableField(exist = false)
    private SysDept dept;

    /**
     * 角色对象
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    /**
     * 角色组
     */
    @TableField(exist = false)
    private Long[] roleIds;

    /**
     * 岗位组
     */
    @TableField(exist = false)
    private Long[] postIds;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
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

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public Timestamp getLogindate() {
        return logindate;
    }

    public void setLogindate(Timestamp logindate) {
        this.logindate = logindate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
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

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return isAdmin(this.userid);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("com.rufeng: ");
        if (null != userid) sb.append("userid=").append(userid).append(",");
        if (null != deptid) sb.append("deptid=").append(deptid).append(",");
        if (null != username) sb.append("username=").append(username).append(",");
        if (null != nickname) sb.append("nickname=").append(nickname).append(",");
        if (null != usertype) sb.append("usertype=").append(usertype).append(",");
        if (null != email) sb.append("email=").append(email).append(",");
        if (null != phonenumber) sb.append("phonenumber=").append(phonenumber).append(",");
        if (null != sex) sb.append("sex=").append(sex).append(",");
        if (null != avatar) sb.append("avatar=").append(avatar).append(",");
        if (null != password) sb.append("password=").append(password).append(",");
        if (null != status) sb.append("status=").append(status).append(",");
        if (null != delflag) sb.append("delflag=").append(delflag).append(",");
        if (null != loginip) sb.append("loginip=").append(loginip).append(",");
        if (null != logindate) sb.append("logindate=").append(logindate).append(",");
        if (null != createby) sb.append("createby=").append(createby).append(",");
        if (null != createtime) sb.append("createtime=").append(createtime).append(",");
        if (null != updateby) sb.append("updateby=").append(updateby).append(",");
        if (null != updatetime) sb.append("updatetime=").append(updatetime).append(",");
        if (null != remark) sb.append("remark=").append(remark).append(",");
        return sb.toString();
    }
}