package com.dugu.test.service.aop.impl;

import com.dugu.test.service.aop.AopService;
import com.dugu.test.service.aop.MonitorLog;
import org.springframework.stereotype.Service;

/**
 * @author cihun
 * @date 2022-04-24 7:23 下午
 */
@Service
public class AopServiceImpl implements AopService {
    @Override
    @MonitorLog
    public int cal(int a, int b) {
        return a+b;
    }
}
