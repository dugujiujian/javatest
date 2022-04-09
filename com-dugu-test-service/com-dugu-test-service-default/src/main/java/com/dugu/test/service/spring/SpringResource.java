package com.dugu.test.service.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @Author cihun
 * @Date 2022-04-04 4:57 下午
 */
@Configuration
public class SpringResource {

    @Bean(name="defaultFile")
    public File defaultFile() {
        File defaultFile = new File("defaultFile.txt");
        return defaultFile;
    }

    @Bean(name="namedFile")
    public File namedFile() {
        File namedFile = new File("namedFile.txt");
        return namedFile;
    }
}