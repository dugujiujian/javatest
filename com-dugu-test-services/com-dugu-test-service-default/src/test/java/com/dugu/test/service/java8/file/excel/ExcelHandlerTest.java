package com.dugu.test.service.java8.file.excel;

import com.dugu.test.service.java8.file.excel.domain.ExcelData1;
import com.dugu.test.service.java8.file.excel.domain.ExcelData2;
import com.dugu.test.service.java8.file.excel.domain.ExcelRequest;
import com.dugu.test.service.java8.file.excel.fixedtitle.ExcelHandlerImpl;
import org.junit.Test;

import java.util.Arrays;

public class ExcelHandlerTest {


    @Test
    public void testExcelData1() {
        ExcelHandlerImpl excelHandler = new ExcelHandlerImpl();
        ExcelRequest<ExcelData1> dataExcelRequest = new ExcelRequest<>();
        ExcelData1 data = ExcelData1.builder().brandId(1001L).build();
        dataExcelRequest.setDataList(Arrays.asList(data));
        String filePath = "//Users/zhaohaihua/Documents";
        String fileName = "excel_data_1.xlsx";
        dataExcelRequest.setFilePath(filePath);
        dataExcelRequest.setFileName(fileName);
        dataExcelRequest.setHead(data);
        excelHandler.exportToExcel(dataExcelRequest);

    }

    @Test
    public void testExcelData2() {
        ExcelHandlerImpl excelHandler = new ExcelHandlerImpl();
        ExcelRequest<ExcelData2> dataExcelRequest = new ExcelRequest<>();
        ExcelData2 data = ExcelData2.builder().cateId(1002L).build();
        dataExcelRequest.setDataList(Arrays.asList(data));
        dataExcelRequest.setHead(data);
        String filePath = "//Users/zhaohaihua/Documents";
        String fileName = "excel_data_2.xlsx";
        dataExcelRequest.setFilePath(filePath);
        dataExcelRequest.setFileName(fileName);
        excelHandler.exportToExcel(dataExcelRequest);
    }

}
