package com.dugu.test.service.java8.file.excel.complex.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2023-02-23 10:24 下午
 */
@Getter
@Setter
@ToString
public class UserScoreValueModel {

    private String userId;
    private String comment;
    private String score;
}
