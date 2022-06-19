package com.dugu.test.service.openapi;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * HmacSHA256签名
 */
public class HmacSHA256Signature {
    /**
     * 默认编码
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 签名算法
     */
    private static final String ALGORITHM = "HmacSHA256";


    private static final Object LOCK = new Object();

    private static Mac macInstance;

    public static HmacSHA256Signature create() {
        return new HmacSHA256Signature();
    }

    public String computeSignature(String key, String data) {
        try {
            byte[] signData = sign(key.getBytes(DEFAULT_ENCODING), data.getBytes(DEFAULT_ENCODING), macInstance,
                    LOCK, ALGORITHM);
            return toBase64String(signData);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Unsupported algorithm: " + DEFAULT_ENCODING, ex);
        }
    }

    private byte[] sign(byte[] key, byte[] data, Mac macInstance, Object lock, String algorithm) {
        try {
            // Because Mac.getInstance(String) calls a synchronized method, it
            // could block on
            // invoked concurrently, so use prototype pattern to improve perf.
            if (macInstance == null) {
                synchronized (lock) {
                    if (macInstance == null) {
                        macInstance = Mac.getInstance(algorithm);
                    }
                }
            }

            Mac mac;
            try {
                mac = (Mac) macInstance.clone();
            } catch (CloneNotSupportedException e) {
                // If it is not clonable, create a new one.
                mac = Mac.getInstance(algorithm);
            }
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Unsupported algorithm: " + algorithm, ex);
        } catch (InvalidKeyException ex) {
            throw new RuntimeException("Invalid key: " + key, ex);
        }
    }

    private String toBase64String(byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }
}
