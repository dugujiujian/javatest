package com.dugu.test.service.retry;

/**
 * @author cihun
 * @date 2022-04-29 3:55 下午
 */
public interface TestRetryService {

    int test(int code);

    int recover(Exception e);
}
