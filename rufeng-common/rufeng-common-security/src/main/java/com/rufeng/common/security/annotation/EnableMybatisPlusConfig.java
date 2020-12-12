package com.rufeng.common.security.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.mybatis.spring.annotation.MapperScans;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义Mybatis Plus注解
 * 添加basePackages路径
 *
 * @author ruoyi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@MapperScan
public @interface EnableMybatisPlusConfig {

    String[] basePackages() default { "com.rufeng.**.mapper" };

}
