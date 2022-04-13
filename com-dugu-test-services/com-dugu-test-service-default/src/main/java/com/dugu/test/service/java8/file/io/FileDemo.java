package com.dugu.test.service.java8.file.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/23 2:16 PM
 */
public class FileDemo {

    public static boolean createDir() {
        try {
            Files.createDirectory(Paths.get("/Users/zhangliping/javatest", "test"));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean deleteDir() {
        try {
            Files.delete(Paths.get("/Users/zhangliping/javatest", "test"));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean createFile() {
        try {
            boolean exist = Files.exists(Paths.get("/Users/zhangliping/javatest/1.txt"));
            if (exist) {
                return true;
            }
            Files.createFile(Paths.get("/Users/zhangliping/javatest/", "1.txt"));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean deleteFile() {
        try {
            boolean exist = Files.exists(Paths.get("/Users/zhangliping/javatest/1.txt"));
            if (exist) {
                Files.delete(Paths.get("/Users/zhangliping/javatest/1.txt"));
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
