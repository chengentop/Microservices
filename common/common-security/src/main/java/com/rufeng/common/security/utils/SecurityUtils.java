package com.rufeng.common.security.utils;

import com.rufeng.common.core.constant.CacheConstants;
import com.rufeng.common.core.text.Convert;
import com.rufeng.common.core.utils.ServletUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 权限获取工具类
 *
 * @author chengen
 */
public class SecurityUtils {
    /**
     * 获取用户
     */
    public static String getUsername() {
        return ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USERNAME);
    }

    /**
     * 获取用户ID
     */
    public static Integer getUserId() {
        return Convert.toInt(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USER_ID));
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Integer userId) {
        return userId != null && 1 == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
