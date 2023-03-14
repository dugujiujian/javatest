package com.dugu.test.service.java8.file.excel.complex.strategy;

import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailExcelModel;
import com.dugu.test.service.java8.file.excel.complex.model.UserScoreValueModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @author cihun
 * @date 2023-03-12 8:18 下午
 */
public class SummarySheetWriteHandler implements SheetWriteHandler {

    private DocDetailExcelModel docDetailExcelModel;

    public SummarySheetWriteHandler(DocDetailExcelModel docDetailExcelModel) {
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

        int startRow = docDetailExcelModel.getDataSize() + 7;


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

        //---自评---
        if (StringUtils.isNotEmpty(docDetailExcelModel.getEmployeeScore())) {
            Row rowEmployee = sheet.createRow(startRow);
            rowEmployee.setHeight((short) 400);
            Cell cell2 = rowEmployee.createCell(0);
            cell2.setCellValue("自评评语");
            cell2.setCellStyle(cellStyleInfo);
            Cell cell21 = rowEmployee.createCell(1);
            cell21.setCellValue("我是来测试的评语");
            cell21.setCellStyle(cellValueStyleInfo);
            sheet.addMergedRegionUnsafe(new CellRangeAddress(1, 1, 1, 2));

            Cell employeeCell = rowEmployee.createCell(docDetailExcelModel.getCellCount() - 2);
            employeeCell.setCellValue("自评总评分");
            employeeCell.setCellStyle(cellStyleInfo);
            Cell cell61 = rowEmployee.createCell(docDetailExcelModel.getCellCount() - 1);
            String employeeV = docDetailExcelModel.getEmployeeScore();
            if (StringUtils.isNotEmpty(docDetailExcelModel.getEmployeeValueScore())) {
                employeeV += docDetailExcelModel.getEmployeeValueScore();
            }
            cell61.setCellValue(employeeV);
            sheet.addMergedRegionUnsafe(new CellRangeAddress(startRow, startRow, 1, 4));
            startRow += 1;
        }
        // 上级评分
        if (StringUtils.isNotEmpty(docDetailExcelModel.getLeaderScoreWeight())
                && CollectionUtils.isNotEmpty(docDetailExcelModel.getLeaderScoreList())) {
            int i = 0;
            for (UserScoreValueModel item : docDetailExcelModel.getLeaderScoreList()) {
                Row rowEmployee = sheet.createRow(startRow);
                rowEmployee.setHeight((short) 400);
                Cell cell2 = rowEmployee.createCell(0);
                cell2.setCellValue("上级评分评语");
                cell2.setCellStyle(cellStyleInfo);
                Cell cell21 = rowEmployee.createCell(1);
                cell21.setCellValue(item.getComment());
                cell21.setCellStyle(cellValueStyleInfo);
                sheet.addMergedRegionUnsafe(new CellRangeAddress(startRow, startRow, 1, 4));
                // 各上级评分合计
                createCell(sheet, cellStyleInfo, cellValueStyleInfo, item, rowEmployee,startRow);
                // 添加上级评分总分
                if (i == 0) {
                    createLeaderScoreTotal(sheet, cellValueStyleInfo, rowEmployee, docDetailExcelModel);
                }
                startRow += 1;
                i++;
            }
            // 上级评分合计cell合并
            sheet.addMergedRegionUnsafe(new CellRangeAddress(startRow - docDetailExcelModel.getLeaderScoreList().size(), startRow - 1, 0, 0));
            sheet.addMergedRegionUnsafe(new CellRangeAddress(startRow - docDetailExcelModel.getLeaderScoreList().size(),
                    startRow - 1, docDetailExcelModel.getCellCount() - 1, docDetailExcelModel.getCellCount() - 1));

        }

    }

    private void createCell(Sheet sheet, CellStyle cellStyleInfo, CellStyle cellValueStyleInfo, UserScoreValueModel item, Row rowEmployee,int startRow) {
        Cell cell3 = rowEmployee.createCell(docDetailExcelModel.getCellCount() - 3);
        cell3.setCellValue("上级总评分-" + item.getUser().getLabel() + "(" + item.getWeight() + "%)");
        cell3.setCellStyle(cellStyleInfo);
        Cell cell31 = rowEmployee.createCell(docDetailExcelModel.getCellCount() - 2);
        if (StringUtils.isNotEmpty(item.getScoreValue())) {
            cell31.setCellValue(item.getScore() + "" + item.getScoreValue());
        } else {
            cell31.setCellValue(item.getScore());
        }

        cell31.setCellStyle(cellValueStyleInfo);
    }

    private void createLeaderScoreTotal(Sheet sheet, CellStyle cellValueStyleInfo, Row rowEmployee, DocDetailExcelModel docDetailExcelModel) {
        Cell cell4 = rowEmployee.createCell(docDetailExcelModel.getCellCount() - 1);
        cell4.setCellValue(docDetailExcelModel.getLeaderScore());
        if (StringUtils.isNotEmpty(docDetailExcelModel.getLeaderValueScore())) {
            cell4.setCellValue(docDetailExcelModel.getLeaderScore() + "" + docDetailExcelModel.getLeaderValueScore());
        } else {
            cell4.setCellValue(docDetailExcelModel.getLeaderScore());
        }
        cell4.setCellStyle(cellValueStyleInfo);
    }


}
