package com.dugu.test.service.retry.impl;

import com.dugu.test.service.retry.TestRetryService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**TestRetryService
 * tip:
 * 1. @EnableRetry：此注解用于开启重试框架，可以修饰在SpringBoot启动类上面，也可以修饰在需要重试的类上
 *    proxyTargetClass：Boolean类型，用于指明代理方式【true：cglib代理，false：jdk动态代理】默认使用jdk动态代理
 * 2. 当重试次数耗尽依然出现异常时，执行此异常对应的@Recover方法。
 *    异常类型需要与Recover方法参数类型保持一致，
 *    recover方法返回值需要与重试方法返回值保证一致
 * 3. Retryable方法只有直接通过代理对象调用时才会生效，通过其他方法间接调用不生效（所有基于AOP的注解都一样）
 *    温馨提示：在Spring中，只有需要用到Aop代理的类才会生成代理对象，不需要Aop的直接返回原生对象
 *
 * @author cihun
 * @date 2022-04-29 3:55 下午
 */
@Service
@EnableRetry
public class TestRetryServiceImpl implements TestRetryService {

    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int test(int code) throws Exception {
        System.out.println("test被调用,时间：" + LocalTime.now());
        if (code == 0) {
            throw new Exception("情况不对头！");
        }
        System.out.println("test被调用,情况对头了！");
        return 200;
    }

    @Recover
    @Override
    public int recover(Exception e) {
        System.out.println("回调方法执行！！！");
        return 200;
    }

}
