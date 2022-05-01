package com.dugu.test.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2022-04-29 4:23 下午
 */
@Getter
@Setter
@ToString
public class Result<T> extends BaseResult {

    private boolean success;
    private String code;
    private String message;
    private T data;


    public Result() {
        this.success = Boolean.TRUE;
    }
}
