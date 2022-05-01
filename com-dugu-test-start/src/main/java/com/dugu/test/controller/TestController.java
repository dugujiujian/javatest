package com.dugu.test.controller;

import com.dugu.test.result.Result;
import com.dugu.test.service.retry.TestRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cihun
 * @date 2022-04-29 4:20 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestRetryService testRetryService;


    @RequestMapping(value = "/springRetry", method = RequestMethod.GET)
    public Result<Boolean> springRetry(int code) {
        Result<Boolean> result = new Result<>();
        try {
             testRetryService.test(code);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setCode("failed");
            result.setMessage(e.getMessage());
            return result;
        }
        return null;
    }

}
