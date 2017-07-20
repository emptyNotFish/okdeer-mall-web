package com.okdeer.mall.utils;


/** 
  * 基础加密组件 
  *  
  * @author 梁栋 
  * @version 1.0 
  * @since 1.0 
  */

/** 
      * MAC算法可选以下多种算法 
      *  
     * <pre> 
      * HmacMD5  
      * HmacSHA1  
      * HmacSHA256  
      * HmacSHA384  
      * HmacSHA512 
      * </pre> 
      */

/** 
      * BASE64解密 
      *  
      * @param key 
      * @return 
      * @throws Exception 
      */

/** 
      * BASE64加密 
      *  
      * @param key 
      * @return 
      * @throws Exception 
      */

/** 
      * MD5加密 
      *  
      * @param data 
      * @return 
      * @throws Exception 
      */

/** 
      * SHA加密 
      *  
      * @param data 
      * @return 
      * @throws Exception 
      */

/** 
      * 初始化HMAC密钥 
     *  
      * @return 
      * @throws Exception 
      */

/** 
      * HMAC加密 
      *  
      * @param data 
     * @param key 
      * @return 
      * @throws Exception 
      */
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;  
  
 /** 
  *  
  * @author 梁栋 
  * @version 1.0 
  * @since 1.0 
  */  
public class CoderTest {  
  
    @Test  
     public void test() throws Exception {  
         String inputStr = "简单加密";  
         System.err.println("原文:\n" + inputStr);  
  
        byte[] inputData = inputStr.getBytes();  
         String code = Coder.encryptBASE64(inputData);  
  
        System.err.println("BASE64加密后:\n" + code);  
  
        byte[] output = Coder.decryptBASE64(code);  
  
        String outputStr = new String(output);  
   
         System.err.println("BASE64解密后:\n" + outputStr);  
   
        // 验证BASE64加密解密一致性  
        assertEquals(inputStr, outputStr);  
  
        // 验证MD5对于同一内容加密是否一致  
        assertArrayEquals(Coder.encryptMD5(inputData), Coder  
                 .encryptMD5(inputData));  
   
        // 验证SHA对于同一内容加密是否一致  
         assertArrayEquals(Coder.encryptSHA(inputData), Coder  
                 .encryptSHA(inputData));  
   
        String key = Coder.initMacKey();  
        System.err.println("Mac密钥:\n" + key);  
  
        // 验证HMAC对于同一内容，同一密钥加密是否一致  
        assertArrayEquals(Coder.encryptHMAC(inputData, key), Coder.encryptHMAC(  
                inputData, key));  
  
        BigInteger md5 = new BigInteger(Coder.encryptMD5(inputData));  
         System.err.println("MD5:\n" + md5.toString(16));  
  
        BigInteger sha = new BigInteger(Coder.encryptSHA(inputData));  
         System.err.println("SHA:\n" + sha.toString(32));  
   
         BigInteger mac = new BigInteger(Coder.encryptHMAC(inputData, inputStr));  
        System.err.println("HMAC:\n" + mac.toString(16));  
     }  
} 

