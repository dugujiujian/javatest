package com.dugu.test.result;


import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author cihun
 * @date 2022-04-29 4:24 下午
 */
public class BaseResult {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
