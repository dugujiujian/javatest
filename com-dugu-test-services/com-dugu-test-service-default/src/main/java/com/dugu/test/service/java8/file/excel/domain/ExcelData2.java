package com.dugu.test.service.java8.file.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import lombok.Builder;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

@Data
@Builder
@HeadFontStyle(fontHeightInPoints = 12)
@HeadStyle(fillPatternType= FillPatternTypeEnum.SOLID_FOREGROUND,fillForegroundColor = 40)
@ColumnWidth(24)
public class ExcelData2  {

    @ExcelProperty(value = "类目ID", index = 0)
    private Long cateId;
}
