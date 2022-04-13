package com.dugu.test.service.java8.file.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelData1 extends BaseRowModel {
    @ExcelProperty(value = "品牌ID", index = 0)
    private Long brandId;
}
