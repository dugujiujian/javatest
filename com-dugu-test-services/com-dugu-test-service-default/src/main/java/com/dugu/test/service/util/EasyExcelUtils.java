package com.dugu.test.service.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dugu.test.util.DateUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    /**
     * 初始化响应体
     *
     * @param response 请求头
     * @param fileName 导出名称
     */
    public static void initResponse(HttpServletResponse response, String fileName) throws IOException {
        // 最终文件名：文件名_(截止yyyy-MM-dd)  --> 这块地方得根据你们自己项目做更改了
        String finalFileName = fileName + "_(截止" + DateUtils.localDateTime2Text(LocalDateTime.now(),DateUtils.DATETIME_PATTERN_FULL) + ")";
        // 设置content—type 响应类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        finalFileName = URLEncoder.encode(finalFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + finalFileName + ".xlsx");
    }
}
