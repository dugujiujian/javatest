package com.dugu.test.service.java8.file.excel.complex.yundong.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户评分模型Title
 *
 * @author cihun
 * @date 2023-02-23 10:18 下午
 */
@Getter
@Setter
@ToString
public class UserScoreHeadModel {
    /**
     * 评分人姓名
     */
    private String label;
    /**
     * 权重
     */
    private String weight;
}
