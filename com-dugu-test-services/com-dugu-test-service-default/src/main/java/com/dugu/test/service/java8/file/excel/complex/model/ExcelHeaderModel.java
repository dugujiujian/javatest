package com.dugu.test.service.java8.file.excel.complex.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2023-03-04 4:41 下午
 */
@Getter
@Setter
@ToString
public class ExcelHeaderModel {
    /**
     * 字段
     */
    private String fieldName;
    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 固定部分
     */
    private Boolean fixed;


}
