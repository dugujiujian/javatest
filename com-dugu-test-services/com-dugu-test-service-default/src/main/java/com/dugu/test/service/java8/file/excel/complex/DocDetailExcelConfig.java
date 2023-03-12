package com.dugu.test.service.java8.file.excel.complex;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2023-03-05 11:38 上午
 */
@Getter
@Setter
@ToString
public class DocDetailExcelConfig {
    /**
     * 自定义头
     */
    private String head;
    /**
     * 是否显示评语
     */
    private Boolean showComment;
    /**
     * 是否显示合计
     */
    private Boolean showTotal;



}
