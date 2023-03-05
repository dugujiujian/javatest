package com.dugu.test.service.java8.file.excel.complex.strategy;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @author cihun
 * @date 2023-03-05 9:03 下午
 */
public class CustomHorizontalCellStyleStrategy {

    private static final short FONT_HEIGHT_IN_POINTS = 12;


    public static HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        // excel 表头格式设置
        WriteCellStyle headCellStyle = new WriteCellStyle();
        //蓝绿色
        headCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints(FONT_HEIGHT_IN_POINTS);
        headWriteFont.setFontName("宋体");
        //字体加粗
        headWriteFont.setBold(Boolean.TRUE);

        //设置字体
        headCellStyle.setWriteFont(headWriteFont);
        //自动换行
        headCellStyle.setWrapped(Boolean.TRUE);
        //垂直居中
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置左右对齐为靠左对齐
        headCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置单元格上下左右边框为细边框
        headCellStyle.setBorderBottom(BorderStyle.THIN);
        headCellStyle.setBorderLeft(BorderStyle.THIN);
        headCellStyle.setBorderRight(BorderStyle.THIN);
        headCellStyle.setBorderTop(BorderStyle.THIN);


        // excel表格内容样式设置
        WriteCellStyle contentCellStyle = new WriteCellStyle();

        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontHeightInPoints(FONT_HEIGHT_IN_POINTS);
        contentWriteFont.setFontName("宋体");
        contentWriteFont.setFontHeightInPoints(FONT_HEIGHT_IN_POINTS);
        contentCellStyle.setWriteFont(contentWriteFont);

        //自动换行
        contentCellStyle.setWrapped(Boolean.TRUE);

        //垂直居中
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);

        return new HorizontalCellStyleStrategy(headCellStyle, contentCellStyle);
    }
}
