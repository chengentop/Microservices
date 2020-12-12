package com.rufeng.common.core.utils.bean;


import com.rufeng.common.core.utils.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 *
 * @author tianhu
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /**
     * Bean方法名中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则表达式
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     *
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * bean转换为map
     *
     * @param bean
     * @return
     * @author zhangkui
     */
    public static Map<String, Object> getFieldValueMap(Object bean) {
        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        if (bean == null) {
            return valueMap;
        }
        PropertyDescriptor[] props = getPropertyDescriptors(bean);
        for (PropertyDescriptor p : props) {
            Method getter = p.getReadMethod();
            if (!"class".equals(p.getName())) {
                try {
                    Object value = getter.invoke(bean);
                    if (null != value) {
                        if (value instanceof String) {
                            valueMap.put(p.getName(), StringUtils.trim((String) value));
                        } else if (value instanceof Date) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            valueMap.put(p.getName(), sdf.format((Date) value));
                        } else if (value instanceof Timestamp) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            valueMap.put(p.getName(), sdf.format(new Date(((Timestamp) value).getTime())));
                        } else {
                            valueMap.put(p.getName(), getter.invoke(bean));
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return valueMap;
    }

    /**
     * list bean转换为list map
     *
     * @param beans bean
     * @return
     * @author zhangkui
     */
    public static List<Map<String, Object>> getFieldValueList(List<?> beans) {
        List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();
        for (Object bean : beans) {
            valueList.add(getFieldValueMap(bean));
        }
        return valueList;
    }

    /**
     * 获取bean属性
     *
     * @param bean
     * @return
     * @author zhangkui
     */
    private static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
        Class<?> cls = bean.getClass();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(cls);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if (null != beanInfo) {
            return beanInfo.getPropertyDescriptors();
        } else {
            return null;
        }
    }
}
