package com.dugu.test.service.java8.file.excel.fixedtitle;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dugu.test.service.java8.file.excel.domain.ExcelRequest;
import com.dugu.test.service.java8.file.excel.domain.ExcelResponse;

/**
 *  多表头导出
 * @author cihun
 * @date 2020-07-29 11:11 PM
 */
public class ExcelHandlerImpl implements ExcelHandler {
    @Override
    public ExcelResponse exportToExcel(ExcelRequest request) {
        ExcelWriter excelWriter = EasyExcel.write(request.getFilePath() + "/" + request.getFileName(), request.getHead().getClass()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
        excelWriter.write(request.getDataList(), writeSheet);
        excelWriter.finish();
        return null;
    }
}
