package com.dugu.test.service.pdf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author cihun
 * @date 2024/5/13 22:30
 */
public class ImageCompressUtils {
    public static final int FILE_SIZE = 3 * 1024; // kb
    public static final int FILE_SIZE_BYTE = 3 * 1024 * 1024;


    public static int getFileSize(String path) {
        int fileSize = 0;
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            fileSize = fis.available();
            return fileSize;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return fileSize;
        } catch (IOException e) {
            e.printStackTrace();
            return fileSize;
        }
    }

}
