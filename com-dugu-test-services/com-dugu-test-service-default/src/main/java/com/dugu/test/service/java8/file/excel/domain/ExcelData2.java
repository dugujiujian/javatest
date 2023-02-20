package com.dugu.test.service.java8.file.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelData2  {
    @ExcelProperty(value = "类目ID", index = 0)
    private Long cateId;
}
