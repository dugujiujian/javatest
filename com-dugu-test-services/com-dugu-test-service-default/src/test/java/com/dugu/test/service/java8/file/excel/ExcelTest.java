package com.dugu.test.service.java8.file.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/5/23 4:01 PM
 */
public class ExcelTest {

    @Test
    public void readExcel() {
        String fileName = "//Users/zhangliping/Documents/yd.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, ExcelTestData.class, new ExcelDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();

    }


    @Test
    public void writeExcel() {

        String fileName = "//Users/zhangliping/Documents/yd.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelTestData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("test").build();
        excelWriter.write(dataFromFile(), writeSheet);
        excelWriter.finish();

    }


    @Test
    public void readDirectory() {
        Path dir = Paths.get("//Users/");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            for (Path file : stream) {
                if (file.toFile().isDirectory()) {
                    System.out.println(file.getFileName());
                }
                ;

            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

    }

    private static List<ExcelTestData> data() {
        List<ExcelTestData> list = new ArrayList<ExcelTestData>();
        for (int i = 0; i < 10; i++) {
            ExcelTestData data = new ExcelTestData();
            data.setBrandId(Long.valueOf(i));
            data.setBrandName("b" + i);
            data.setMerchantUserId(Long.valueOf(i));
            data.setCompanyName("c" + i);
            data.setMerchantSource("供应商");
            list.add(data);
        }
        return list;
    }

    public List<ExcelTestData> dataFromFile() {
        String fileName = "//Users/zhangliping/Documents/s.txt";
        // Java 8例子
        List<String> lines = null;
        try {
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8).stream()
                    .map(item -> StringUtils.split(item, ","))
                    .map(ExcelTest::buildExcelTestData)
                    .collect(Collectors.toList());


        } catch (IOException e) {
//            StackTraceElement elements[] = e.getStackTrace();
//            for (int i = 0, n = elements.length; i < n; i++) {
//                System.out.println(Level.WARNING+"--"+elements[i].getMethodName());
//            }
//            StackTraceElement elements[] = e.getStackTrace();
//            for (int i = 0, n = elements.length; i < n; i++) {
//                System.err.println(elements[i].getFileName()
//                        + ":" + elements[i].getLineNumber()
//                        + ">> "
//                        + elements[i].getMethodName() + "()");
//            }
        }
        return Collections.emptyList();
    }

    private static ExcelTestData buildExcelTestData(String[] arr) {
        ExcelTestData data = new ExcelTestData();
        data.setBrandId(Long.valueOf(arr[0]));
        data.setBrandName(arr[1]);
        data.setMerchantUserId(Long.valueOf(arr[2]));
        data.setCompanyName(arr[3]);
        data.setMerchantSource(arr[4]);
        return data;
    }

}
