package com.dugu.test.service.openapi;

/**
 * openapi响应体
 */
public class OpenApiResponse {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;

    /**
     * 成功标识
     */
    private boolean success;

    /**
     * 数据
     * 不同的接口，返回的data类型不同
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
