package com.dugu.test.service.java8.file.excel.complex.model;

import com.dugu.test.service.performance.domain.UserSimpleDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 文档详情头部信息模型
 *
 * @author cihun
 * @date 2023-02-23 11:25 下午
 */
@Getter
@Setter
@ToString
public class DocDetailExcelModel {
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 部门路径
     */
    private String departmentPath;
    /**
     * 文档用户
     */
    private UserSimpleDTO user;
    /**
     * 岗位
     */
    private String position;

    /**
     * 主管
     */
    private UserSimpleDTO leader;

    /**
     * 考核周期
     */
    private String planCycle;
    /**
     * 总分
     */
    private String totalScore;
    /**
     * 价值观
     */
    private String totalValueScore;
    /**
     * 等级
     */
    private String grade;

    /**
     * 总cell总数
     */
    private Integer cellCount;

    /**
     * 总cell总数
     */
    private Integer dataSize;
    /**
     * 自评权重
     */
    private String employeeWeight;
    /**
     * 邀评权重
     */
    private String inviteWeight;
    /**
     * 上级评分权重
     */
    private String leaderScoreWeight;

    /**
     * 自评总分
     */
    private String employeeScore;
    /**
     * 自评价值观
     */
    private String employeeValueScore;

    /**
     * 同事互评评分
     */
    private String inviteScore;
    /**
     * 上级评分
     */
    private String leaderScore;
    /**
     * 自评价值观
     */
    private String leaderValueScore;
    /**
     * 上级评分
     */
    private List<UserScoreValueModel> leaderScoreList;


}
