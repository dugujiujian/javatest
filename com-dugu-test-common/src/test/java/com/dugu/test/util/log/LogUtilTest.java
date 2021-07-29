package com.dugu.test.util.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogUtilTest {


    @Test
    public void testDebug() {
        log.isDebugEnabled();
        LogUtil.debug(log, "{0},{1}", "s", "1");
    }

    @Test
    public void testInfo() {
        LogUtil.info(log, "{0},{1}", "s", 2);
    }

}