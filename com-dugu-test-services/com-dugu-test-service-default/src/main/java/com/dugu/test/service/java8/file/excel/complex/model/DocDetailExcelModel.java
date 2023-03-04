package com.dugu.test.service.java8.file.excel.complex.model;

import com.dugu.test.service.performance.domain.UserSimpleDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
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
}
