package com.dugu.test.service.java8.file.excel.complex.fixed;

import com.dugu.test.service.java8.file.excel.domain.ExcelData1;
import com.dugu.test.service.java8.file.excel.domain.ExcelData2;
import com.dugu.test.service.java8.file.excel.domain.ExcelRequest;
import com.dugu.test.service.java8.file.excel.fixedtitle.ExcelHandlerImpl;

import java.util.Arrays;

/**
 * @author by cihun
 * @Description 导出excel
 * @date 2024/5/3 22:41
 */
public class ExportExcel {

    public static final String EXCEL_DATA1 = "excel_data1";
    public static final String EXCEL_DATA2 = "excel_data12";


    public String exportExcelWithDiffExcelHeadModel(String excelExportType) {
        if (EXCEL_DATA1.equals(excelExportType)) {
            ExcelHandlerImpl excelHandler = new ExcelHandlerImpl();
            ExcelRequest<ExcelData1> dataExcelRequest = new ExcelRequest<>();
            ExcelData1 data = ExcelData1.builder().brandId(1001L).build();
            dataExcelRequest.setDataList(Arrays.asList(data));
            String filePath = "//Users/zhangliping/Document";
            String fileName = "excel_data_1.xlsx";
            dataExcelRequest.setFilePath(filePath);
            dataExcelRequest.setFileName(fileName);
            dataExcelRequest.setHead(data);
            excelHandler.exportToExcel(dataExcelRequest);
            return filePath+fileName;
        } else if (EXCEL_DATA2.equals(excelExportType)) {
            ExcelHandlerImpl excelHandler = new ExcelHandlerImpl();
            ExcelRequest<ExcelData2> dataExcelRequest = new ExcelRequest<>();
            ExcelData2 data = ExcelData2.builder().cateId(1002L).build();
            dataExcelRequest.setDataList(Arrays.asList(data));
            dataExcelRequest.setHead(data);
            String filePath = "//Users/zhangliping/Document";
            String fileName = "excel_data_2.xlsx";
            dataExcelRequest.setFilePath(filePath);
            dataExcelRequest.setFileName(fileName);
            excelHandler.exportToExcel(dataExcelRequest);
            return filePath+fileName;
        }
        return null;
    }
}
