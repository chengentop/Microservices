package com.rufeng.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
  * sysoperlog 数据持久化对象
  * @version v1.0.0
  * @since jdk1.8+
  */
@TableName("sys_oper_log")
public class SysOperLog implements Serializable{
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long operid;  //日志主键
	private String title;  //模块标题
	private Integer businesstype;  //业务类型（0其它 1新增 2修改 3删除）
	private String method;  //方法名称
	private String requestmethod;  //请求方式
	private Integer operatortype;  //操作类别（0其它 1后台用户 2手机端用户）
	private String opername;  //操作人员
	private String deptname;  //部门名称
	private String operurl;  //请求URL
	private String operip;  //主机地址
	private String operlocation;  //操作地点
	private String operparam;  //请求参数
	private String jsonresult;  //返回参数
	private Integer status;  //操作状态（0正常 1异常）
	private String errormsg;  //错误消息
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
	private Timestamp opertime;  //操作时间
	
	public Long getOperid(){
		return this.operid;
	}
	public void setOperid(Long operid){
		this.operid = operid;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public Integer getBusinesstype(){
		return this.businesstype;
	}
	public void setBusinesstype(Integer businesstype){
		this.businesstype = businesstype;
	}
	public String getMethod(){
		return this.method;
	}
	public void setMethod(String method){
		this.method = method;
	}
	public String getRequestmethod(){
		return this.requestmethod;
	}
	public void setRequestmethod(String requestmethod){
		this.requestmethod = requestmethod;
	}
	public Integer getOperatortype(){
		return this.operatortype;
	}
	public void setOperatortype(Integer operatortype){
		this.operatortype = operatortype;
	}
	public String getOpername(){
		return this.opername;
	}
	public void setOpername(String opername){
		this.opername = opername;
	}
	public String getDeptname(){
		return this.deptname;
	}
	public void setDeptname(String deptname){
		this.deptname = deptname;
	}
	public String getOperurl(){
		return this.operurl;
	}
	public void setOperurl(String operurl){
		this.operurl = operurl;
	}
	public String getOperip(){
		return this.operip;
	}
	public void setOperip(String operip){
		this.operip = operip;
	}
	public String getOperlocation(){
		return this.operlocation;
	}
	public void setOperlocation(String operlocation){
		this.operlocation = operlocation;
	}
	public String getOperparam(){
		return this.operparam;
	}
	public void setOperparam(String operparam){
		this.operparam = operparam;
	}
	public String getJsonresult(){
		return this.jsonresult;
	}
	public void setJsonresult(String jsonresult){
		this.jsonresult = jsonresult;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getErrormsg(){
		return this.errormsg;
	}
	public void setErrormsg(String errormsg){
		this.errormsg = errormsg;
	}
	public Timestamp getOpertime(){
		return this.opertime;
	}
	public void setOpertime(Timestamp opertime){
		this.opertime = opertime;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("com.rufeng: ");
		if(null != operid)sb.append("operid=").append(operid).append(",");
		if(null != title)sb.append("title=").append(title).append(",");
		if(null != businesstype)sb.append("businesstype=").append(businesstype).append(",");
		if(null != method)sb.append("method=").append(method).append(",");
		if(null != requestmethod)sb.append("requestmethod=").append(requestmethod).append(",");
		if(null != operatortype)sb.append("operatortype=").append(operatortype).append(",");
		if(null != opername)sb.append("opername=").append(opername).append(",");
		if(null != deptname)sb.append("deptname=").append(deptname).append(",");
		if(null != operurl)sb.append("operurl=").append(operurl).append(",");
		if(null != operip)sb.append("operip=").append(operip).append(",");
		if(null != operlocation)sb.append("operlocation=").append(operlocation).append(",");
		if(null != operparam)sb.append("operparam=").append(operparam).append(",");
		if(null != jsonresult)sb.append("jsonresult=").append(jsonresult).append(",");
		if(null != status)sb.append("status=").append(status).append(",");
		if(null != errormsg)sb.append("errormsg=").append(errormsg).append(",");
		if(null != opertime)sb.append("opertime=").append(opertime).append(",");
		return sb.toString();
	}
}