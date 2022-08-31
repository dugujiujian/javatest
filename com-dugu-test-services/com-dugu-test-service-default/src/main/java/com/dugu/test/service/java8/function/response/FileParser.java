package com.dugu.test.service.java8.function.response;

/**
 * @author cihun
 * @date 2022-08-30 11:29 下午
 */
public interface FileParser {
    /**
     * 解析文件
     *
     * @param file
     * @return
     */
    String parse(File file);

    /**
     * 下一个节点
     *
     * @param next
     */
    void setNextParser(FileParser next);
}
