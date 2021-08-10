package com.rufeng.system.business.domain.po;

/**
 * 当前在线用户信息
 */
public class SysUserOnline {
    private static final long serialVersionUID = 1L;
    private String tokenId; //会话编号
    private String username; //用户名称
    private String ipaddr; //登录IP地址
    private String loginlocation; //登录地址
    private String browser; //浏览器类型
    private String os; //操作系统
    private Long loginTime; //登录时间

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginlocation() {
        return loginlocation;
    }

    public void setLoginlocation(String loginlocation) {
        this.loginlocation = loginlocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
}
