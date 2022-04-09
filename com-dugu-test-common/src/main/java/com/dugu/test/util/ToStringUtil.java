package com.dugu.test.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author cihun
 * @Date 2022-04-09 9:17 下午
 */
public class ToStringUtil {

    public static String toString(String obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
