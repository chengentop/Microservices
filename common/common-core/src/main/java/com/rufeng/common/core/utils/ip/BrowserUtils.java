package com.rufeng.common.core.utils.ip;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther chengen
 * @date 2021/7/10 14:30
 */
public class BrowserUtils {
    /**
     * 获取浏览器信息
     * @param request
     * @return 浏览器名称及版本
     */
    public static String getBrowserName(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        Browser browser = ua.getBrowser();
        return browser.getName() + " " + browser.getVersion(userAgent);
    }

    /**
     * 获取请求的操作系统类型
     * @param request
     * @return 系统名称
     */
    public static String getOsName(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        String osName = ua.getOperatingSystem().getName();
        return osName;
    }
}
