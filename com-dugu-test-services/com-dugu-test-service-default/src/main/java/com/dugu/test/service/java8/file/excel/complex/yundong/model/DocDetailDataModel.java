package com.dugu.test.service.java8.file.excel.complex.yundong.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author cihun
 * @date 2023-03-04 11:39 下午
 */
@Getter
@Setter
@ToString
public class DocDetailDataModel {

    /**
     * 维度名称
     */
    private String dimensionName;
    /**
     * 维度权重
     */
    private String dimensionWeight;
    /**
     * 评分上限
     */
    private String upperLimit;
    /**
     * 目标名称
     */
    private String objectName;
    /**
     * 描述
     */
    private String desc;
    /**
     * 标准
     */
    private String standard;
    /**
     * 完成说明
     */
    private String readme;
    /**
     * 开始值
     */
    private String startValue;
    /**
     * 目标值
     */
    private String targetValue;
    /**
     * 挑战值
     */
    private String chargeValue;
    /**
     * 完成值
     */
    private String completeValue;
    /**
     * 完成度
     */
    private String completeDegree;
    /**
     * 单位
     */
    private String unit;
    /**
     * 权重
     */
    private String weight;

    /**
     * 自评合计
     */
    private String objectEmployeeTotal;
    /**
     * 同事互评合计
     */
    private String objectInviteTotal;
    /**
     * 上级评分合计
     */
    private String objectLeaderTotal;
    /**
     * 目标合计
     */
    private String objectTotalScore;

    /**
     * 自评用户
     */
    private UserScoreValueModel employeeScore;
    /**
     * 邀请评分
     */
    private List<UserScoreValueModel> inviteScoreList;
    /**
     * 上级评分
     */
    private List<UserScoreValueModel> leaderScoreList;
}
