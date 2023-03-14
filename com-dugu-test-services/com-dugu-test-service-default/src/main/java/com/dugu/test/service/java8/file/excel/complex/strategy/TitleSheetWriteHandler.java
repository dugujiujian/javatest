package com.dugu.test.service.java8.file.excel.complex.strategy;

import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailExcelModel;
import org.apache.poi.ss.usermodel.BorderStyle;
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
 * 标题
 *
 * @author cihun
 * @date 2023-02-23 11:23 下午
 */
public class TitleSheetWriteHandler implements SheetWriteHandler {

    private DocDetailExcelModel docDetailExcelModel;

    public TitleSheetWriteHandler(DocDetailExcelModel docDetailExcelModel) {
        this.docDetailExcelModel = docDetailExcelModel;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = writeSheetHolder.getSheet();
        // 标题
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 800);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue(docDetailExcelModel.getPlanName());
        CellStyle cellStyle = workbook.createCellStyle();
        setAlignCenter(cellStyle);
        setFont(workbook, cellStyle, 400);
        cell1.setCellStyle(cellStyle);

        //设置其他信息的单元格样式
        CellStyle cellStyleInfo = workbook.createCellStyle();
        setAlignCenter(cellStyleInfo);
        setBg(cellStyleInfo);
        setBorderStyle(cellStyleInfo);
        setFont(workbook, cellStyleInfo, 200);

        CellStyle cellValueStyleInfo = workbook.createCellStyle();
        setAlignCenter(cellValueStyleInfo);
        setBorderStyle(cellValueStyleInfo);
        setFont(workbook, cellValueStyleInfo, 200);

        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, docDetailExcelModel.getCellCount() - 1));

        //---部门---
        Row row = sheet.createRow(1);
        row.setHeight((short) 400);
        Cell cell2 = row.createCell(0);
        cell2.setCellValue("部门");
        cell2.setCellStyle(cellStyleInfo);
        Cell cell21 = row.createCell(1);
        cell21.setCellValue(docDetailExcelModel.getDepartmentPath());
        cell21.setCellStyle(cellValueStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 1, 2));
        //---姓名---
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("姓名");
        cell3.setCellStyle(cellStyleInfo);
        Cell cell31 = row.createCell(4);
        cell31.setCellValue(docDetailExcelModel.getUser().getLabel());
        cell31.setCellStyle(cellValueStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 4, 5));
        //---岗位---
        Cell cell4 = row.createCell(6);
        cell4.setCellValue("岗位");
        cell4.setCellStyle(cellStyleInfo);
        Cell cell41 = row.createCell(7);
        cell41.setCellValue(docDetailExcelModel.getPosition());
        cell41.setCellStyle(cellValueStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 7, 8));

        Cell cell5 = row.createCell(9);
        cell5.setCellValue("主管");
        cell5.setCellStyle(cellStyleInfo);
        Cell cell51 = row.createCell(10);
        cell51.setCellValue(docDetailExcelModel.getLeader().getLabel());
        cell51.setCellStyle(cellValueStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 10, 11));

        Cell cell6 = row.createCell(docDetailExcelModel.getCellCount() - 3);
        cell6.setCellValue("周期");
        cell6.setCellStyle(cellStyleInfo);
        Cell cell61 = row.createCell(docDetailExcelModel.getCellCount() - 2);
        cell61.setCellValue(docDetailExcelModel.getPlanCycle());
        cell61.setCellStyle(cellValueStyleInfo);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, docDetailExcelModel.getCellCount() - 2, docDetailExcelModel.getCellCount() - 1));

    }

    private void setFont(Workbook workbook, CellStyle cellStyle, int value) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) value);
        cellStyle.setFont(font);
    }

    private void setBorderStyle(CellStyle valueCellStyle) {
        //设置边框样式
        valueCellStyle.setBorderLeft(BorderStyle.THIN);
        valueCellStyle.setBorderTop(BorderStyle.THIN);
        valueCellStyle.setBorderRight(BorderStyle.THIN);
        valueCellStyle.setBorderBottom(BorderStyle.THIN);

        valueCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        valueCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        valueCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        valueCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
    }

    private void setAlignCenter(CellStyle cellStyleInfo) {
        //设置边框样式
        cellStyleInfo.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleInfo.setAlignment(HorizontalAlignment.CENTER);
    }

    private void setBg(CellStyle cellStyleInfo) {
        //设置边框样式
        cellStyleInfo.setFillPattern(FillPatternTypeEnum.SOLID_FOREGROUND.getPoiFillPatternType());
        cellStyleInfo.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
    }
}
