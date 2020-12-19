package com.rufeng.system.business.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
  * syslogininfor 数据持久化对象
  * @version v1.0.0
  * @since jdk1.8+
  */
@TableName("sys_logininfor")
public class SysLogininfor implements Serializable{
	private static final long serialVersionUID = 1L;    
	
	private Integer infoid;  //访问ID
	private String username;  //用户账号
	private String ipaddr;  //登录IP地址
	private String loginlocation;  //登录地点
	private String browser;  //浏览器类型
	private String os;  //操作系统
	private String status;  //登录状态（0成功 1失败）
	private String msg;  //提示消息
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
	private Timestamp logintime;  //访问时间
	
	public Integer getInfoid(){
		return this.infoid;
	}
	public void setInfoid(Integer infoid){
		this.infoid = infoid;
	}
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getIpaddr(){
		return this.ipaddr;
	}
	public void setIpaddr(String ipaddr){
		this.ipaddr = ipaddr;
	}
	public String getLoginlocation(){
		return this.loginlocation;
	}
	public void setLoginlocation(String loginlocation){
		this.loginlocation = loginlocation;
	}
	public String getBrowser(){
		return this.browser;
	}
	public void setBrowser(String browser){
		this.browser = browser;
	}
	public String getOs(){
		return this.os;
	}
	public void setOs(String os){
		this.os = os;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getMsg(){
		return this.msg;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	public Timestamp getLogintime(){
		return this.logintime;
	}
	public void setLogintime(Timestamp logintime){
		this.logintime = logintime;
	}

}