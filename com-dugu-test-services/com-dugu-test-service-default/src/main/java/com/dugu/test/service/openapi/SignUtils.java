package com.dugu.test.service.openapi;

/**
 * 签名工具
 */
public class SignUtils {
    /**
     * 签名
     *
     * @param accessKey 密钥accessKey
     * @param message   待签名字符串
     * @return
     */
    public static String sign(String accessKey, String message) {
        return HmacSHA256Signature.create().computeSignature(accessKey, message);
    }
}
