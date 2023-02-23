package com.dugu.test.service.java8.file.excel.complex.strategy;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.dugu.test.service.java8.file.excel.complex.model.DocExcelModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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

    private DocExcelModel docExcelModel;

    public BudgetDeclareSheetWriteHandler(DocExcelModel docExcelModel) {
        this.docExcelModel = docExcelModel;
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

        //设置标题
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 800);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue(docExcelModel.getPlanName());
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
        //合并单元格
        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, 22));
        //设置基本信息
        Row row2 = sheet.createRow(1);
        row2.setHeight((short) 400);
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("部门：" + docExcelModel.getDepartment());
        cell2.setCellStyle(cellStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 0, 1));
        Cell cell23 = row2.createCell(21);
        cell23.setCellValue("职位：" + docExcelModel.getPosition());
        cell23.setCellStyle(cellStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 21, 22));

        Row row3 = sheet.createRow(2);
        row3.setHeight((short) 400);
        Cell cell3 = row3.createCell(0);
        cell3.setCellValue("总分：" + docExcelModel.getTotalScore());
        cell3.setCellStyle(cellStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(2, 2, 0, 1));
        Cell cell33 = row3.createCell(21);
        cell33.setCellValue("等级：" + docExcelModel.getGrade());
        cell33.setCellStyle(cellStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(2, 2, 21, 22));

        Row row4 = sheet.createRow(3);
        row4.setHeight((short) 400);
        Cell cell6 = row4.createCell(0);
        cell6.setCellValue("备注：");
        cell6.setCellStyle(cellStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(3, 4, 0, 22));
    }
}
