package com.dugu.test.service.yundong;

import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author cihun
 * @date 2023/8/24 21:49
 */
public class EncryptUtils {
    private static final String KEY = "XUBP1kaYm4q/1rCt8U+n6V1AT9ZGmJuK";

    // 加密算法
    private static final String ALGORITHM = "DESede";
    // 转换模式
    private static final String TRANSFORMATION = "DESede";

    public static String encrypt(String plainText) {
        try {
            // 实例化密码对象
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 实例化秘钥工厂
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            DESedeKeySpec desKeySpec = new DESedeKeySpec(KEY.getBytes(StandardCharsets.UTF_8));
            SecretKey desSecretKey = secretKeyFactory.generateSecret(desKeySpec);
            // 设置模式（ENCRYPT_MODE：加密模式；DECRYPT_MODE：解密模式）和指定秘钥
            cipher.init(Cipher.ENCRYPT_MODE, desSecretKey);
            // 加密
            byte[] encrypt = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptText) {
        try {
            // 实例化密码对象
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // 实例化秘钥工厂
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            DESedeKeySpec desKeySpec = new DESedeKeySpec(KEY.getBytes(StandardCharsets.UTF_8));
            SecretKey desSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            // 设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, desSecretKey);
            byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptText));
            return new String(decrypt);
        } catch (Exception e) {
            throw new RuntimeException("EncryptUtils.decrypt error.", e);
        }
    }

    public static void main(String[] args) throws Exception {
        String plainText = "a432d59443b44d50bbb387cb64ede5c2";
        String encrypted = encrypt(plainText);
        System.out.println(encrypted);

        String decrypt = decrypt(encrypted);
        System.out.println(decrypt);

        Assert.isTrue(plainText.equals(decrypt), "解密后字符串应该与加密前一致");

    }

}
