package com.dugu.test.service.java8.file.excel.complex;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cihun
 * @date 2023-02-19 1:07 下午
 */
public class ComplexWriteExcelTest extends TestCase {


    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("维度");
        list.add(head0);
        List<String> head1 = new ArrayList<>();
        head1.add("目标");
        list.add(head1);
        List<String> head2 = new ArrayList<>();
        head2.add("权重");
        list.add(head2);
        Map<String, List<String>> map = getHeader();
        map.forEach((k, v) -> {
            String deviceCategory = k;
            List<String> ls = v;
            ls.forEach(e -> {
                List<String> head = new ArrayList<>();
                head.add(deviceCategory);
                head.add(e);
                list.add(head);
            });

        });

        return list;
    }

    /**
     * 下载模板的自定义表头第二行
     *
     * @return
     */
    private static Map<String, List<String>> getHeader() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> aList = new ArrayList<>();
        List<String> sList = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        aList.add("刺魂");
        sList.add("刺魂(10%)");
        sList.add("99(90%)");
        map.put("自评（10%）", aList);
        map.put("上级评分(90%)", sList);
        return map;
    }

    @Test
    public void test() {
        List<List<String>> header = head();
        String fileName = "//Users/zhaohaihua/Documents/complex.xlsx";
        EasyExcel.write(fileName)
                .head(header)
                .sheet("文档").doWrite(Collections.EMPTY_LIST);
    }

    /**
     * 思路:
     * 动态表格构建思路，表头可以认为是一个二维数组，按下面的列的数字去构建内存的数组，内层的数组元素的个数就是表头列的合并单元格后的行数
     * <pre>
     *     List<List<Object>> 二维数组
     *     List<Object> 内层数组
     * </pre>
     *
     * @throws FileNotFoundException
     */
    @Test
    public void test2() throws FileNotFoundException {
        String fileName = "//Users/zhaohaihua/Documents/complex2.xlsx";
        //创建ExcelWriter写入对象
        ExcelWriter excelWriter = EasyExcel.write(new FileOutputStream(fileName)).build();

        //创建Sheet对象
        WriteSheet sheet = new WriteSheet();
        //设置第N个Sheet
        sheet.setSheetNo(1);
        //设置Sheet名称
        sheet.setSheetName("文档2");

        //创建表格对象
        WriteTable table = new WriteTable();
        //设置第N个表格
        table.setTableNo(1);


        //创建表头集合
        List<List<String>> headList = new ArrayList<List<String>>();

        //第N列的表头
        List<String> headTitle1 = new ArrayList<>();
        List<String> headTitle2 = new ArrayList<>();
        List<String> headTitle3 = new ArrayList<>();
        List<String> headTitle4 = new ArrayList<>();
        List<String> headTitle5 = new ArrayList<>();
        List<String> headTitle6 = new ArrayList<>();
        List<String> headTitle7 = new ArrayList<>();
        List<String> headTitle8 = new ArrayList<>();
        List<String> headTitle9 = new ArrayList<>();
        List<String> headTitle10 = new ArrayList<>();
        List<String> headTitle11 = new ArrayList<>();
        List<String> headTitle12 = new ArrayList<>();
        List<String> headTitle13 = new ArrayList<>();
        List<String> headTitle14 = new ArrayList<>();
        List<String> headTitle15 = new ArrayList<>();
        List<String> headTitle16 = new ArrayList<>();
        List<String> headTitle17 = new ArrayList<>();
        List<String> headTitle18 = new ArrayList<>();
        List<String> headTitle19 = new ArrayList<>();
        List<String> headTitle20 = new ArrayList<>();
        List<String> headTitle21 = new ArrayList<>();
        List<String> headTitle22 = new ArrayList<>();
        headTitle1.add("维度");
        //Dau
        headTitle2.add("目标");
        //第三列
        headTitle3.add("权重");
        //第四列
        headTitle4.add("描述");
        //第五列
        headTitle5.add("标准");
        //第六列
        headTitle6.add("完成度");
        //第七列
        headTitle7.add("完成值");
        //第八列
        headTitle8.add("单位");
        //第九列
        headTitle9.add("完成说明");
        //===============自评==================
        //第十列
        headTitle10.add("自评(10%)");
        headTitle10.add("刺魂");
        headTitle10.add("评分");
        //第11列
        headTitle11.add("自评(10%)");
        headTitle11.add("刺魂");
        headTitle11.add("评语");
        //===============同事互评==================
        //第12列
        headTitle12.add("同事互评");
        headTitle12.add("刺魂（80%）");
        headTitle12.add("评分");
        //第13列
        headTitle13.add("同事互评");
        headTitle13.add("刺魂（80%）");
        headTitle13.add("评语");
        //第14列
        headTitle14.add("同事互评");
        headTitle14.add("99（20%）");
        headTitle14.add("评分");
        //第15列
        headTitle15.add("同事互评");
        headTitle15.add("99（20%）");
        headTitle15.add("评语");
        //===============行为-游戏==================
        //第16列
        headTitle16.add("上级评分");
        headTitle16.add("刺魂(10%)");
        headTitle16.add("评分");
        //第17列
        headTitle17.add("上级评分");
        headTitle17.add("刺魂(10%)");
        headTitle17.add("评语");
        //第18列
        headTitle18.add("上级评分");
        headTitle18.add("99(90%)");
        headTitle18.add("评分");
        //第19列
        headTitle19.add("上级评分");
        headTitle19.add("99(90%)");
        headTitle19.add("评语");


        headList.add(headTitle1);
        headList.add(headTitle2);
        headList.add(headTitle3);
        headList.add(headTitle4);
        headList.add(headTitle5);
        headList.add(headTitle6);
        headList.add(headTitle7);
        headList.add(headTitle8);
        headList.add(headTitle9);
        headList.add(headTitle10);
        headList.add(headTitle11);
        headList.add(headTitle12);
        headList.add(headTitle13);
        headList.add(headTitle14);
        headList.add(headTitle15);
        headList.add(headTitle16);
        headList.add(headTitle17);
        headList.add(headTitle18);
        headList.add(headTitle19);

        table.setHead(headList);
        List<List<Object>> list = new ArrayList<>();
        excelWriter.write(list, sheet, table);
        // 记得 释放资源
        excelWriter.finish();
    }

}