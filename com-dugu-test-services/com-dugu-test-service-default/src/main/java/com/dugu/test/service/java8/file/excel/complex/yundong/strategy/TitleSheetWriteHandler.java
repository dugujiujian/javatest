package com.dugu.test.service.java8.file.excel.complex.yundong.strategy;

import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.dugu.test.service.java8.file.excel.complex.yundong.model.DocDetailExcelModel;
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
import org.apache.poi.ss.util.RegionUtil;

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
        setFont(workbook, cellStyle, 12,true);
        cell1.setCellStyle(cellStyle);

        //设置其他信息的单元格样式
        CellStyle cellStyleInfo = workbook.createCellStyle();
        setAlignCenter(cellStyleInfo);
        setBg(cellStyleInfo);
        setBorderStyle(cellStyleInfo);
        setFont(workbook, cellStyleInfo, 12,true);

        CellStyle cellValueStyleInfo = workbook.createCellStyle();
        setAlignCenter(cellValueStyleInfo);
        setBorderStyle(cellValueStyleInfo);
        setFont(workbook, cellValueStyleInfo, 11,false);

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

        CellRangeAddress cellRangeAddress1= new CellRangeAddress(1, 1, 1, 3);
        sheet.addMergedRegionUnsafe(cellRangeAddress1);
        setRegion(sheet,cellRangeAddress1);

        //---姓名---
        Cell cell3 = row.createCell(4);
        cell3.setCellValue("姓名");
        cell3.setCellStyle(cellStyleInfo);
        Cell cell31 = row.createCell(5);
        cell31.setCellValue(docDetailExcelModel.getUser().getLabel());
        cell31.setCellStyle(cellValueStyleInfo);

        CellRangeAddress cellRangeAddress2= new CellRangeAddress(1, 1, 5, 6);
        sheet.addMergedRegionUnsafe(cellRangeAddress2);
        setRegion(sheet,cellRangeAddress2);

        //---岗位---
        Cell cell4 = row.createCell(7);
        cell4.setCellValue("岗位");
        cell4.setCellStyle(cellStyleInfo);
        Cell cell41 = row.createCell(8);
        cell41.setCellValue(docDetailExcelModel.getPosition());
        cell41.setCellStyle(cellValueStyleInfo);

        CellRangeAddress cellRangeAddress3= new CellRangeAddress(1, 1, 8, 9);
        sheet.addMergedRegionUnsafe(cellRangeAddress3);
        setRegion(sheet,cellRangeAddress3);

        Cell cell5 = row.createCell(10);
        cell5.setCellValue("主管");
        cell5.setCellStyle(cellStyleInfo);
        Cell cell51 = row.createCell(11);
        cell51.setCellValue(docDetailExcelModel.getLeader().getLabel());
        cell51.setCellStyle(cellValueStyleInfo);

        CellRangeAddress cellRangeAddress4= new CellRangeAddress(1, 1, 11, docDetailExcelModel.getCellCount() - 4);
        sheet.addMergedRegionUnsafe(cellRangeAddress4);
        setRegion(sheet,cellRangeAddress4);

        Cell cell6 = row.createCell(docDetailExcelModel.getCellCount() - 3);
        cell6.setCellValue("周期");
        cell6.setCellStyle(cellStyleInfo);
        Cell cell61 = row.createCell(docDetailExcelModel.getCellCount() - 2);
        cell61.setCellValue(docDetailExcelModel.getPlanCycle());
        cell61.setCellStyle(cellValueStyleInfo);

        CellRangeAddress cellRangeAddress5= new CellRangeAddress(1, 1, docDetailExcelModel.getCellCount() - 2, docDetailExcelModel.getCellCount() - 1);
        sheet.addMergedRegionUnsafe(cellRangeAddress5);
        setRegion(sheet,cellRangeAddress5);

    }

    private void setRegion(Sheet sheet,CellRangeAddress rangeAddress){
        RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
    }

    private void setFont(Workbook workbook, CellStyle cellStyle, int value,boolean bold) {
        Font font = workbook.createFont();
        font.setBold(bold);
        font.setFontHeightInPoints((short)value);
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
