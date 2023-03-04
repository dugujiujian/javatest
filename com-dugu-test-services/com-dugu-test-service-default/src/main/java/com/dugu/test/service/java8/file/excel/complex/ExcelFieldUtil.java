package com.dugu.test.service.java8.file.excel.complex;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author cihun
 * @date 2023-03-04 3:05 下午
 */
public class ExcelFieldUtil {

    private static Map<String, String> DOC_NAME_FIELD_MAP = Maps.newHashMap();
    private static Map<String, String> DOC_FIELD_NAME_MAP = Maps.newHashMap();

    {
        DOC_NAME_FIELD_MAP.put("维度", "dimensionName");
        DOC_NAME_FIELD_MAP.put("目标", "objectName");
        DOC_NAME_FIELD_MAP.put("描述", "desc");
        DOC_NAME_FIELD_MAP.put("标准", "start");
        DOC_NAME_FIELD_MAP.put("完成说明", "readme");
        DOC_NAME_FIELD_MAP.put("初始值", "startVale");
        DOC_NAME_FIELD_MAP.put("目标值", "targetVale");
        DOC_NAME_FIELD_MAP.put("挑战值", "chargeValue");
        DOC_NAME_FIELD_MAP.put("完成度", "completeDegree");
        DOC_NAME_FIELD_MAP.put("完成值", "completeVale");
        DOC_NAME_FIELD_MAP.put("单位", "unit");
        DOC_NAME_FIELD_MAP.put("权重", "weight");
        DOC_FIELD_NAME_MAP.put("单项计分","item_score");

        DOC_FIELD_NAME_MAP.put("dimensionName", "维度");
        DOC_FIELD_NAME_MAP.put("objectName", "目标");
        DOC_FIELD_NAME_MAP.put("desc", "描述");
        DOC_FIELD_NAME_MAP.put("start", "标准");
        DOC_FIELD_NAME_MAP.put("readme", "完成说明");
        DOC_FIELD_NAME_MAP.put("startVale", "初始值");
        DOC_FIELD_NAME_MAP.put("targetVale", "目标值");
        DOC_FIELD_NAME_MAP.put("chargeValue", "挑战值");
        DOC_FIELD_NAME_MAP.put("completeDegree", "完成度");
        DOC_FIELD_NAME_MAP.put("completeVale", "完成值");
        DOC_FIELD_NAME_MAP.put("unit", "单位");
        DOC_FIELD_NAME_MAP.put("weight", "权重");


        DOC_FIELD_NAME_MAP.put("item_score", "单项计分");
    }

    public static String getNameFieldByKey(String key) {
        if (StringUtils.isNotEmpty(key)) {
            return DOC_NAME_FIELD_MAP.get(key);
        }
        return null;
    }

}
