package com.dugu.test.service.openapi;

import java.util.HashMap;
import java.util.Map;

/**
 * openapi请求
 */
public class OpenApiRequest {
    /**
     * 请求数据
     */
    private Map<String, Object> data = new HashMap<>(8);

    public String toJsonString() {
        return GsonUtils.toJSONString(this);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
