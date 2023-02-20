package com.dugu.test.service.java8.file.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelData1  {
    @ExcelProperty(value = "品牌ID", index = 0)
    private Long brandId;
}
