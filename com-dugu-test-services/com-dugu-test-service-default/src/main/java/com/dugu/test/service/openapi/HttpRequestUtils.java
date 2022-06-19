package com.dugu.test.service.openapi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * http请求工具类
 */
public class HttpRequestUtils {
    private static final String DEFAULT_CHAR_SET = "UTF-8";

    public static String post(String url, Map<String, Object> headers, String body) throws IOException {
        HttpRequestBase request = new HttpPost();
        request.setURI(URI.create(url));
        return request(request, headers, body);
    }

    private static String request(HttpRequestBase request, Map<String, Object> headers, String body) throws IOException {
        // 设置header
        setHeader(request, headers);

        // 设置body
        setBody(request, body);

        // http调用
        return getString(request);
    }

    private static void setHeader(HttpRequestBase request, Map<String, Object> headers) {
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            if (entry != null && entry.getKey() != null) {
                request.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    private static void setBody(HttpRequestBase request, String body) {
        if (body != null && request instanceof HttpEntityEnclosingRequestBase) {
            HttpEntity entity = EntityBuilder.create()
                    .setContentEncoding(DEFAULT_CHAR_SET)
                    .setContentType(ContentType.APPLICATION_JSON)
                    .setText(body)
                    .build();
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
    }

    private static String getString(HttpRequestBase request) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        try {
            if (null == response || null == response.getStatusLine()) {
                throw new RuntimeException("请求结果无法解析！");
            }
            return EntityUtils.toString(response.getEntity(), DEFAULT_CHAR_SET);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (response != null && response instanceof CloseableHttpResponse) {
                ((CloseableHttpResponse) response).close();
            }
        }
    }

}
