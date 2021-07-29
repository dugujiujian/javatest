package com.dugu.test.util.log;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.text.MessageFormat;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void debug(Logger logger, String format, Object... arg) {
        if (logger.isDebugEnabled()) {
            logger.debug(formatString(format, arg));
        }
    }

    public static void info(Logger logger, String pattern, Object... arg) {
        info(logger, false, pattern, arg);
    }


    public static void info(Logger logger, boolean hiddenLog, String pattern, Object... arg) {
        if (hiddenLog) {
            return;
        }
        logger.info(formatString(pattern, arg));
    }


    public static void error(Throwable e, Logger logger, String format, Object... arg) {
        String errorCode = getErrorCode(e);
        logger.warn("{},ErrorCode={}", formatString(format, arg), errorCode, e);
    }

    public static void warn(Throwable e, Logger logger, boolean hiddenLog, String format, Object... arg) {
        if (hiddenLog) {
            return;
        }
        String errorCode = getErrorCode(e);
        logger.warn("{},ErrorCode={}", formatString(format, arg), errorCode, e);
    }


    private static String formatString(String pattern, Object... parameters) {
        if (parameters == null
                || parameters.length == 0
                || StringUtils.isEmpty(pattern)) {
            return StringUtils.EMPTY;
        }
        StringBuffer log = new StringBuffer();
        try {
            Object[] objects = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof String) {
                    objects[i] = parameters[i];
                } else {
                    objects[i] = JSONObject.toJSONString(parameters[i]);
                }
            }
            log.append(MessageFormat.format(pattern, objects));
        } catch (Exception e) {
            logger.error("[LogUtil]format log pattern error:{}", pattern, e);
            log.append(pattern);
        }
        return log.toString();
    }

    private static String getErrorCode(Throwable ex) {
        if (ex == null) {
            return StringUtils.EMPTY;
        }
        try {
            Method mt = ex.getClass().getDeclaredMethod("getErrorCode");
            if (mt != null) {
                Object obj = mt.invoke(ex);
                if (obj != null) {
                    return obj.toString();
                }
            }
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
        return StringUtils.EMPTY;
    }

}
