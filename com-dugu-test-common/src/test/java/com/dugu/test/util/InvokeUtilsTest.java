package com.dugu.test.util;


import com.dugu.test.util.log.domain.StudentLog;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Author cihun
 * @Date 2022-04-02 11:59 下午
 */
public class InvokeUtilsTest {


    @Test
    public void testGetField() {
        Field[] fields = InvokeUtils.getAllField(StudentLog.class);
        Arrays.stream(fields).forEach(item-> System.out.println(item.getName()));
        Assert.assertEquals(2, fields.length);
    }


}