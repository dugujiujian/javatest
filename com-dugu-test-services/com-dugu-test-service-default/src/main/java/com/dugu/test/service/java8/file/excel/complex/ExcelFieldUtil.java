package com.dugu.test.service.java8.file.excel.complex;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author cihun
 * @date 2023-03-04 3:05 下午
 */
public class ExcelFieldUtil {

    public static Map<String, String> DOC_NAME_FIELD_MAP = Maps.newHashMap();
    public static Map<String, String> DOC_FIELD_NAME_MAP = Maps.newHashMap();

    static {
        DOC_NAME_FIELD_MAP.put("维度", "dimensionName");
        DOC_NAME_FIELD_MAP.put("目标", "objectName");
        DOC_NAME_FIELD_MAP.put("描述", "desc");
        DOC_NAME_FIELD_MAP.put("标准", "standard");
        DOC_NAME_FIELD_MAP.put("完成说明", "readme");
        DOC_NAME_FIELD_MAP.put("初始值", "startValue");
        DOC_NAME_FIELD_MAP.put("目标值", "targetValue");
        DOC_NAME_FIELD_MAP.put("挑战值", "chargeValue");
        DOC_NAME_FIELD_MAP.put("完成度", "completeDegree");
        DOC_NAME_FIELD_MAP.put("完成值", "completeValue");
        DOC_NAME_FIELD_MAP.put("单位", "unit");
        DOC_NAME_FIELD_MAP.put("权重", "weight");

        DOC_NAME_FIELD_MAP.put("自评合计", "objectEmployeeTotal");
        DOC_NAME_FIELD_MAP.put("同事互评合计", "objectInviteTotal");
        DOC_NAME_FIELD_MAP.put("上级评分合计", "objectLeaderTotal");
        DOC_NAME_FIELD_MAP.put("目标合计", "objectTotalScore");

        DOC_FIELD_NAME_MAP.put("dimensionName", "维度");
        DOC_FIELD_NAME_MAP.put("objectName", "目标");
        DOC_FIELD_NAME_MAP.put("desc", "描述");
        DOC_FIELD_NAME_MAP.put("standard", "标准");
        DOC_FIELD_NAME_MAP.put("readme", "完成说明");
        DOC_FIELD_NAME_MAP.put("startValue", "初始值");
        DOC_FIELD_NAME_MAP.put("targetValue", "目标值");
        DOC_FIELD_NAME_MAP.put("chargeValue", "挑战值");
        DOC_FIELD_NAME_MAP.put("completeDegree", "完成度");
        DOC_FIELD_NAME_MAP.put("completeValue", "完成值");
        DOC_FIELD_NAME_MAP.put("unit", "单位");
        DOC_FIELD_NAME_MAP.put("weight", "权重");

        DOC_FIELD_NAME_MAP.put("objectEmployeeTotal", "自评合计");
        DOC_FIELD_NAME_MAP.put("objectInviteTotal", "同事互评合计");
        DOC_FIELD_NAME_MAP.put("objectLeaderTotal", "上级评分合计");
        DOC_FIELD_NAME_MAP.put("objectTotalScore", "目标合计");
    }

    public static String getNameFieldByName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return DOC_NAME_FIELD_MAP.get(name);
        }
        return null;
    }

}
