package com.dugu.test.service.retry;

import com.dugu.test.service.retry.impl.TestRetryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试代码不行，需要走controller
 *
 * @author cihun
 * @date 2022-04-29 3:57 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRetryServiceTest.Config.class)
public class TestRetryServiceTest {

    @Autowired
    private TestRetryService testRetryService;

    @Test
    public void testRetryServiceF() {
        try {
            testRetryService.test(0);
        } catch (Exception e) {
           // e.printStackTrace();
        }

    }

    @Configuration
    static class Config {

        @Bean
        public TestRetryService testRetryService() {
            return new TestRetryServiceImpl();
        }
    }
}
