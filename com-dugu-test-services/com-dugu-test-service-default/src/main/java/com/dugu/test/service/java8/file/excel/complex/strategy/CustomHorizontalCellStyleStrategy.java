package com.dugu.test.service.java8.file.excel.complex.strategy;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cihun
 * @date 2023-03-05 9:03 下午
 */
public class CustomHorizontalCellStyleStrategy {

    public static HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //表头前景设置淡蓝色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setBold(true);
        headWriteFont.setFontName("宋体");
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteCellStyle.setWriteFont(headWriteFont);

        //内容样式  多个样式则隔行换色
        List<WriteCellStyle> listCntWritCellSty = new ArrayList<>();

        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置自动换行
        contentWriteCellStyle.setWrapped(true);
        //设置垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置边框样式
        setBorderStyle(contentWriteCellStyle);
        //内容风格可以定义多个。
        listCntWritCellSty.add(contentWriteCellStyle);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, listCntWritCellSty);

    }

    /**
     * 设置边框样式
     *
     * @param contentWriteCellStyle
     */
    private static void setBorderStyle(WriteCellStyle contentWriteCellStyle) {
        //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
    }

}
