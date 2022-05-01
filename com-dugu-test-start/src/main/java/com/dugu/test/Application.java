package com.dugu.test;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;

/**
 * @description TODO
 * @author by cihun
 * @date 2020/6/12 10:49 PM
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        new SpringApplicationBuilder(Application.class)
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}
