package com.dugu.test.service.java8.file.excel;

import com.dugu.test.service.java8.file.excel.complex.fixed.ExportExcel;
import org.junit.Test;

public class ExportExcelTest {


    @Test
    public void testExcelData1() {
        ExportExcel excel=new ExportExcel();
        excel.exportExcelWithDiffExcelHeadModel(ExportExcel.EXCEL_DATA1);
    }

    @Test
    public void testExcelData2() {
        ExportExcel excel=new ExportExcel();
        excel.exportExcelWithDiffExcelHeadModel(ExportExcel.EXCEL_DATA2);
    }

}
