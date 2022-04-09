package com.dugu.test.enums;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * 纳税人类型
 *
 * @Author cihun
 * @Date 2022-04-09 9:24 下午
 */
public enum TaxEnum {

    /**
     * 普通纳税人
     */
    COMMON_TAXER(1001, "普通纳税人"),
    /**
     * 规模纳税人
     */
    GM_TAXER(1001, "规模纳税人");

    /**
     * 编码
     */
    @Getter
    private Integer code;
    /**
     * 描述
     */
    @Getter
    private String desc;


    private static final Map<Integer, TaxEnum> TAX_ENUM_MAP = Maps.newHashMap();

    static {
        for (TaxEnum e : TaxEnum.values()) {
            TAX_ENUM_MAP.put(e.getCode(), e);
        }
    }


    TaxEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TaxEnum getTaxerByCode(Integer code) {
        if (code != null) {
            return TAX_ENUM_MAP.get(code);
        }
        return null;
    }


}
