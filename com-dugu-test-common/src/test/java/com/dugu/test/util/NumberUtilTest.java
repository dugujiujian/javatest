package com.dugu.test.util;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author cihun
 * @Date 2022-04-02 11:48 下午
 */
public class NumberUtilTest {


    @Test
    public void testFenToYuan() {
        BigDecimal b =
                NumberUtil.fenToYuan(BigDecimal.valueOf(1200), 0, RoundingMode.HALF_UP);
        Assert.assertEquals(12, b.intValue());

        b =
                NumberUtil.fenToYuan(BigDecimal.valueOf(1223), 2, RoundingMode.HALF_UP);
        Assert.assertEquals(BigDecimal.valueOf(12.23), b);

        b =
                NumberUtil.fenToYuan(BigDecimal.valueOf(1223), 1, RoundingMode.HALF_UP);
        Assert.assertEquals(BigDecimal.valueOf(12.2), b);
        b =
                NumberUtil.fenToYuan(BigDecimal.valueOf(1226), 1, RoundingMode.HALF_UP);
        Assert.assertEquals(BigDecimal.valueOf(12.3), b);
    }


}