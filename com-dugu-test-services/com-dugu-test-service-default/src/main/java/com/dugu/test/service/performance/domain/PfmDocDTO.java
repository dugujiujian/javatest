package com.dugu.test.service.performance.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author cihun
 * @date 2022-07-23 10:12 上午
 */
@Getter
@Setter
@ToString
public class PfmDocDTO implements Serializable {

    private String docId;
    private String planId;
    private String templateId;

    private UserSimpleDTO user;
    private UserSimpleDTO leader;
    private DepartDTO department;

}
