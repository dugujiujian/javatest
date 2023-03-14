package com.dugu.test.service.java8.file.excel.complex;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author cihun
 * @date 2023-03-04 3:05 下午
 */
public class ExcelFieldUtil {

    public final static String VALUE_EMPLOYEE_SCORE = "employeeScore";
    public final static String VALUE_INVITE_SCORE = "inviteScoreList";
    public final static String VALUE_LEADER_SCORE = "leaderScoreList";
    public final static String VALUE_OBJECT_EMPLOYEE_TOTAL = "objectEmployeeTotal";
    public final static String NAME_OBJECT_EMPLOYEE_TOTAL = "自评合计";
    public final static String VALUE_OBJECT_INVITE_SCORE = "objectInviteTotal";
    public final static String NAME_OBJECT_INVITE_SCORE = "同事互评合计";
    public final static String VALUE_OBJECT_LEADER_SCORE = "objectLeaderTotal";
    public final static String NAME_OBJECT_LEADER_SCORE = "上级评分合计";
    public final static String VALUE_OBJECT_TOTAL_SCORE = "objectTotalScore";
    public final static String NAME_OBJECT_TOTAL_SCORE = "合计";


    public final static String NAME_SCORE = "评分";
    public final static String VALUE_SCORE = "score";
    public final static String NAME_COMMENT = "评语";
    public final static String VALUE_COMMENT = "comment";


    public static Map<String, String> DOC_NAME_FIELD_MAP = Maps.newHashMap();
    public static Map<String, String> DOC_FIELD_NAME_MAP = Maps.newHashMap();

    static {
        DOC_NAME_FIELD_MAP.put("维度", "dimensionName");
        DOC_NAME_FIELD_MAP.put("维度权重(%)", "dimensionWeight");
        DOC_NAME_FIELD_MAP.put("分数上限", "upperLimit");
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
        DOC_NAME_FIELD_MAP.put("权重(%)", "weight");

        DOC_FIELD_NAME_MAP.put("评语", "comment");
        DOC_FIELD_NAME_MAP.put("评分", "score");

        DOC_NAME_FIELD_MAP.put(NAME_OBJECT_EMPLOYEE_TOTAL, VALUE_OBJECT_EMPLOYEE_TOTAL);
        DOC_NAME_FIELD_MAP.put(NAME_OBJECT_INVITE_SCORE, VALUE_OBJECT_INVITE_SCORE);
        DOC_NAME_FIELD_MAP.put(NAME_OBJECT_LEADER_SCORE, VALUE_OBJECT_LEADER_SCORE);
        DOC_NAME_FIELD_MAP.put(NAME_OBJECT_TOTAL_SCORE, VALUE_OBJECT_TOTAL_SCORE);

        DOC_NAME_FIELD_MAP.put("自评评分", VALUE_EMPLOYEE_SCORE);
        DOC_NAME_FIELD_MAP.put("同事互评评分", VALUE_INVITE_SCORE);
        DOC_NAME_FIELD_MAP.put("上级评分", VALUE_LEADER_SCORE);


        DOC_FIELD_NAME_MAP.put("dimensionName", "维度");
        DOC_FIELD_NAME_MAP.put("dimensionWeight", "维度权重(%)");
        DOC_FIELD_NAME_MAP.put("upperLimit", "分数上限");
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
        DOC_FIELD_NAME_MAP.put("weight", "权重(%)");

        DOC_FIELD_NAME_MAP.put("comment", "评语");
        DOC_FIELD_NAME_MAP.put("score", "评分");

        DOC_FIELD_NAME_MAP.put(VALUE_OBJECT_EMPLOYEE_TOTAL, NAME_OBJECT_EMPLOYEE_TOTAL);
        DOC_FIELD_NAME_MAP.put(VALUE_OBJECT_INVITE_SCORE, NAME_OBJECT_INVITE_SCORE);
        DOC_FIELD_NAME_MAP.put(VALUE_OBJECT_LEADER_SCORE, NAME_OBJECT_LEADER_SCORE);
        DOC_FIELD_NAME_MAP.put(VALUE_OBJECT_TOTAL_SCORE, NAME_OBJECT_TOTAL_SCORE);


        DOC_FIELD_NAME_MAP.put(VALUE_EMPLOYEE_SCORE, "自评评分");
        DOC_FIELD_NAME_MAP.put(VALUE_INVITE_SCORE, "同事互评评分");
        DOC_FIELD_NAME_MAP.put(VALUE_LEADER_SCORE, "上级评分");


    }

    public static String getNameFieldByName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return DOC_NAME_FIELD_MAP.get(name);
        }
        return null;
    }

}
