package com.dugu.test.service.java8.file.excel.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExcelRequest<T> {

    private String filePath;
    private String fileName;
    private List<T> dataList;
    private T head;
}
