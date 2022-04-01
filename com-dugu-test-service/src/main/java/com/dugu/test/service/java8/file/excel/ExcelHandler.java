package com.dugu.test.service.java8.file.excel;

import com.dugu.test.service.java8.file.excel.domain.ExcelRequest;
import com.dugu.test.service.java8.file.excel.domain.ExcelResponse;

/**
 * Excel 处理
 */

public interface ExcelHandler {

    /**
     * 导出到excel
     *
     * @param request
     * @return
     */
    ExcelResponse exportToExcel(ExcelRequest request);
}
