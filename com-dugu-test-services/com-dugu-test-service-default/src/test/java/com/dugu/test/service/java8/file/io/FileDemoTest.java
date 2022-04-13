package com.dugu.test.service.java8.file.io;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/23 2:25 PM
 */
public class FileDemoTest {

    @Test
    public void createDir() {
        FileDemo.createDir();
    }

    @Test
    public void deleteDir() {
        FileDemo.deleteDir();
    }

    @Test
    public void createFile() {
        FileDemo.createFile();
    }

    @Test
    public void createDeleteFile() {
        FileDemo.deleteFile();
    }
}