package com.okdeer.mall.utils;

package com.okdeer.mall.common.utils.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang3.Validate;
import org.apache.tomcat.util.bcel.classfile.Constant;

import com.okdeer.base.common.utils.Exceptions;


/**
 * 支持SHA-1/MD5消息摘要的工具类.加盐和加密
 * 
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 * 
 * @author calvin
 */
public class Digests {

    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";

    private static SecureRandom random = new SecureRandom();

    /**
     * 对输入字符串进行sha1散列.
     */
    public static byte[] sha1(byte[] input) {
        return digest(input, SHA1, null, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, SHA1, salt, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 生成随机的Byte[]作为salt.
     * 
     * @param numBytes byte数组的大小
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对文件进行md5散列.
     */
    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, MD5);
    }

    /**
     * 对文件进行sha1散列.
     */
    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, SHA1);
    }

    private static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String encode(String password,String salt){
        byte[] saltByte = Encodes.decodeHex(salt);

        byte[] hashPassword = Digests.sha1(password.getBytes(),saltByte, Constant.HASH_INTERATIONS);

        return Encodes.encodeHex(hashPassword);
    }

public static void main(String[] args) {
        byte[] salt = generateSalt(SALT_SIZE);
        System.out.println(salt);
        byte[] hashPassword =
                Digests.sha1("123456".getBytes(), salt,
                HASH_INTERATIONS);
        System.out.println(hashPassword);
    }
    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(SysUser sysUser) {
        //TODO
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        sysUser.setSalt(Encodes.encodeHex(salt)); 
       
        byte[] hashPassword =
        Digests.sha1(sysUser.getPlainPassword().getBytes(), salt,
        HASH_INTERATIONS);
        //hash_interations
        sysUser.setPassword(Encodes.encodeHex(hashPassword));
    }
}

