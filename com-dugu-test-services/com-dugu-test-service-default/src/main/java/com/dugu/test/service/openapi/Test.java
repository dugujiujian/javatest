package com.dugu.test.service.openapi;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试case
 */
public class Test {
    private static final String YUNDONG_OPEN_ACCESS_ID = "Yundong-Open-AccessId";
    private static final String YUNDONG_OPEN_AC_TIMESTAMP = "Yundong-Open-Ac-Timestamp";
    private static final String YUNDONG_OPEN_AC_SIGNATURE = "Yundong-Open-Ac-Signature";

    /**
     * 访问ID
     */
    private static final String accessId = "5qPfc9tUZjFoMI5UTwDa7Ugi";
    /**
     * 访问密钥
     */
    private static final String accessKey = "ZxFc1CtFwBNdjzf5XbWIbaDMDYALnIUC";

    public static void main(String[] args) throws IOException{
        caseOne();
    }

    private static void caseOne() throws IOException {
        // 当前时间戳
        long timestamp = System.currentTimeMillis();
        // 接口地址
        String url = "http://192.168.11.186:8080/gateway/open/v2/api/MyOaPurchaseRecord_self_nosub";
        // 构造请求
        OpenApiRequest openApiRequest =  new OpenApiRequest();
        openApiRequest.getData().put("pageNo","1");
        openApiRequest.getData().put("pageSize","10");
        openApiRequest.getData().put("user_id","bbffbcde239a4c51a2a40ed2eeca66e5");
        // 生成签名,带上时间戳、accessId
        StringBuffer sb = new StringBuffer();
        sb.append(accessId);
        sb.append(timestamp);
        sb.append(openApiRequest.toJsonString());
        String signature = SignUtils.sign(accessKey, sb.toString());
        // 构造http header
        Map<String, Object> headers = new HashMap<>(8);
        headers.put(YUNDONG_OPEN_ACCESS_ID, accessId);
        headers.put(YUNDONG_OPEN_AC_TIMESTAMP, timestamp);
        headers.put(YUNDONG_OPEN_AC_SIGNATURE, signature);
        String result = HttpRequestUtils.post(url,headers,openApiRequest.toJsonString());
        OpenApiResponse openApiResponse = GsonUtils.parseObject(result, OpenApiResponse.class);
        System.out.println(String.format("code=%s,message=%s,success=%s,data=%s",openApiResponse.getCode(),
                openApiResponse.getMessage(),openApiResponse.isSuccess(), JSONObject.toJSONString(openApiResponse.getData())));
    }
}