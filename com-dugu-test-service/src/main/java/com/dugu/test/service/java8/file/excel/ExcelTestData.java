package com.dugu.test.service.java8.file.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/5/23 4:20 PM
 */
@Setter
@Getter
@ToString
public class ExcelTestData {
    @ExcelProperty(value = "品牌ID", index = 0)
    private Long brandId;
    @ExcelProperty(value = "品牌名称", index = 1)
    private String brandName;
    @ExcelProperty(value = "商家ID", index = 2)
    private Long merchangUserId;
    @ExcelProperty(value = "商品名称", index = 3)
    private String companyName;
    @ExcelProperty(value = "商家类型", index = 4)
    private String merchantSource;
}
