package com.dugu.test.service.performance.aop;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cihun
 * @date 2022-07-23 3:24 下午
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessCodeType {

    /**
     * 枚举类型
     *
     * @return
     */
    ProcessCodeEnum type();
}
