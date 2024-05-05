package com.dugu.test.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类
 *
 * @author cihun
 * @date 2024/5/5 18:48
 */
public class DateUtils {


    public static final String DATETIME_PATTERN_FULL = "yyyyMMddHHmmss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
    public static final String DATE_STR_PATTERN = "yyyy年MM月dd日";
    public static final DateTimeFormatter DATE_STR_FORMATTER = DateTimeFormatter.ofPattern(DATE_STR_PATTERN);

    private static final Map<String, DateTimeFormatter> formatters = new HashMap<>();

    static {
        formatters.put(DATE_PATTERN, DATE_FORMATTER);
        formatters.put(DATETIME_PATTERN, DATETIME_FORMATTER);
    }


    public static String localDateTime2Text(LocalDateTime dateTime) {
        return localDateTime2Text(dateTime, null);
    }

    public static String localDateTime2Text(LocalDateTime dateTime, String pattern) {
        pattern = pattern == null ? DATETIME_PATTERN : pattern;
        if(formatters.get(pattern) == null) {
            formatters.put(pattern, DateTimeFormatter.ofPattern(pattern));
        }
        return formatters.get(pattern).format(dateTime);
    }



}
