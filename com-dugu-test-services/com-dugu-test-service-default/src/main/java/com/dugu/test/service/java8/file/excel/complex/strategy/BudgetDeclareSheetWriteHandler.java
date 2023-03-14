package com.dugu.test.service.java8.file.excel.complex.strategy;

import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailExcelModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author cihun
 * @date 2023-02-23 11:23 下午
 */
public class BudgetDeclareSheetWriteHandler implements SheetWriteHandler {

    private DocDetailExcelModel docDetailExcelModel;

    public BudgetDeclareSheetWriteHandler(DocDetailExcelModel docDetailExcelModel) {
        this.docDetailExcelModel = docDetailExcelModel;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = writeSheetHolder.getSheet();
        //设置表格宽度
        //sheet.setColumnWidth(0, 30 * 256);
        //sheet.setColumnWidth(1, 30 * 256);
        //sheet.setColumnWidth(2, 30 * 256);
        //sheet.setColumnWidth(3, 40 * 256);
        //sheet.setColumnWidth(4, 30 * 256);


        // 标题
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 800);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue(docDetailExcelModel.getPlanName());
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);


        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 400);
        cellStyle.setFont(font);
        cell1.setCellStyle(cellStyle);
        //设置其他信息的单元格样式
        CellStyle cellStyleInfo = workbook.createCellStyle();
        cellStyleInfo.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleInfo.setAlignment(HorizontalAlignment.CENTER);
        cellStyleInfo.setFillPattern(FillPatternTypeEnum.SOLID_FOREGROUND.getPoiFillPatternType());
        cellStyleInfo.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        Font celFont = workbook.createFont();
        celFont.setBold(true);
        cellStyleInfo.setFont(celFont);


        CellStyle cellValueStyleInfo = workbook.createCellStyle();
        cellValueStyleInfo.setVerticalAlignment(VerticalAlignment.CENTER);
        cellValueStyleInfo.setAlignment(HorizontalAlignment.CENTER);
        cellValueStyleInfo.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

        Font celValueFont = workbook.createFont();
        celValueFont.setFontHeight((short) 200);
        cellValueStyleInfo.setFont(celValueFont);
        //合并单元格
        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, docDetailExcelModel.getCellCount() - 1));


        //---部门---
        Row row2 = sheet.createRow(1);
        row2.setHeight((short) 400);
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("部门");
        cell2.setCellStyle(cellStyleInfo);
        Cell cell21 = row2.createCell(1);
        cell21.setCellValue(docDetailExcelModel.getDepartmentPath());
        cell21.setCellStyle(cellValueStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 1, 2));
        //---姓名---
        Cell cell3 = row2.createCell(3);
        cell3.setCellValue("姓名");
        cell3.setCellStyle(cellStyleInfo);
        Cell cell31 = row2.createCell(4);
        cell31.setCellValue(docDetailExcelModel.getUser().getLabel());
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 4, 5));
        //---岗位---
        Cell cell4 = row2.createCell(6);
        cell4.setCellValue("岗位");
        cell4.setCellStyle(cellStyleInfo);
        Cell cell41 = row2.createCell(7);
        cell41.setCellValue(docDetailExcelModel.getPosition());
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 7, 8));

        Cell cell5 = row2.createCell(9);
        cell5.setCellValue("直接上级");
        cell5.setCellStyle(cellStyleInfo);
        Cell cell51 = row2.createCell(10);
        cell51.setCellValue(docDetailExcelModel.getLeader().getLabel());
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 10, 11));

        Cell cell6 = row2.createCell(docDetailExcelModel.getCellCount() - 3);
        cell6.setCellValue("周期");
        cell6.setCellStyle(cellStyleInfo);
        Cell cell61 = row2.createCell(docDetailExcelModel.getCellCount() - 2);
        cell61.setCellValue(docDetailExcelModel.getPlanCycle());
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, docDetailExcelModel.getCellCount() - 2, docDetailExcelModel.getCellCount() - 1));

    }
}
