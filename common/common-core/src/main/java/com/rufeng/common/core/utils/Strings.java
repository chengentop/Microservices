package com.rufeng.common.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Strings {
    private static Logger log = LoggerFactory.getLogger(Strings.class);
    private static Random random = new Random();

    /**
     * 获取任意字符随机串  <b>字母(含大小写)、数字</b>
     */
    public static String produceRandomStringByAllChar(int length) {
        CharSequence allChar = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 62);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    /**
     * 获取字符随机串 <b>字母(仅大写)、数字</b>
     */
    public static String produceRandomStringByUpperChar(int length) {
        CharSequence allChar = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 36);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    /**
     * 获取字符随机串 <b>字母(仅小写)、数字</b>
     */
    public static String produceRandomStringByLowerChar(int length) {
        CharSequence allChar = "abcdefghigklmnopqrstuvwxyz0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 36);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }

    /**
     * 获取数字随机串
     */
    public static String produceRandomStringByNumber(int length) {
        CharSequence allChar = "0123456789";
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt() % 10);
            result.append(allChar.charAt(index));
        }
        return result.toString();
    }
}
