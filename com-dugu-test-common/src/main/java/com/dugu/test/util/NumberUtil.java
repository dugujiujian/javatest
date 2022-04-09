package com.dugu.test.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额处理工具类
 *
 * @Author cihun
 * @Date 2022-04-01 9:34 下午
 */
public class NumberUtil {

    /**
     * 分转元, no scale，no RoundingMode
     *
     * @param b 要转的分
     * @return
     */
    public static BigDecimal fenToYuan(BigDecimal b) {
        return b.divide(BigDecimal.valueOf(100));
    }

    /**
     * 分转元,四舍五入,保留位数
     *
     * @param b     要转的分
     * @param scale 保留位数
     * @return
     */
    public static BigDecimal fenToYuan(BigDecimal b, int scale) {
        return fenToYuan(b, scale, RoundingMode.HALF_UP);
    }

    /**
     * 分转元,基础转化方法
     *
     * @param b            要转的分
     * @param scale        保留位数
     * @param roundingMode 取位数模式
     * @return
     */
    public static BigDecimal fenToYuan(BigDecimal b, int scale, RoundingMode roundingMode) {
        return fenToYuan(b).setScale(scale, roundingMode);
    }

    /**
     * 元转分
     *
     * @param b
     * @return
     */
    public static BigDecimal yuanToFen(BigDecimal b) {
        return b.multiply(BigDecimal.valueOf(100));
    }

}
