package com.dugu.test;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author cihun
 * @date 2020/04/20
 */
public class AnyTest {

    public static void main(String[] args) {
//        String input = "a/b{/}b/c{/}{/}c";
//        String output = convertString(input);
//        System.out.println(output);

        BigDecimal c = (new BigDecimal("0.34"))
                .multiply(new BigDecimal("10")).setScale(0, RoundingMode.CEILING);
        System.out.println(c.toString());

    }

    public static String convertString(String input) {
        String[] parts = StringUtils.replace(input, "{/}", "#@#").split("/");

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.contains("#@#")) {
                part = part.replace("#@#", "/");
            }
            output.append(part).append(" ");
        }

        return output.toString();
    }

}

