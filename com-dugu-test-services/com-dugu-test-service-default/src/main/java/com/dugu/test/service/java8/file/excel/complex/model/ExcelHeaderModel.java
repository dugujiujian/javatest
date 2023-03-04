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
     * 名称
     */
    private String label;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 排序
     */
    private Integer sort;
}
