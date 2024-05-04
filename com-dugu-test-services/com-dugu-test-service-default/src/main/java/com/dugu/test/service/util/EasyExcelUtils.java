package com.dugu.test.service.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2024/5/4 09:15
 */
public class EasyExcelUtils {


    public static void writeDynamicExcel(HttpServletResponse response,
                                         String fileName, String sheetName,
                                         List<List<String>> headList, List<List<Object>> dataList) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" + "filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream())
                // 设置动态头
                .head(headList).sheet(sheetName).doWrite(dataList);
    }

    public static void writeDynamicBatchExcel(HttpServletResponse response,
                                              String fileName, String sheetName,
                                              List<List<String>> headList, List<List<Object>> dataList) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" + "filename*=utf-8''" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        //循环输出sheet和表头以及表格内容
        for (int i = 0; i < dataList.size(); i++) {
            WriteSheet sheet = EasyExcel.writerSheet(i, sheetName + (i + 1)).head(Collections.singletonList(headList.get(i))).build();
            excelWriter.write(dataList.get(i), sheet);
        }
        excelWriter.finish();
    }
}
