package com.dugu.test.service.aop;

import com.dugu.test.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cihun
 * @date 2022-04-24 9:30 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MonitorLogAopInterceptorTest {


    @Autowired
    private AopService aopService;

    @Test
    public void test(){
        aopService.cal(1,2);
    }


}
