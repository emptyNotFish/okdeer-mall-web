package com.okdeer.mall.utils;


 import java.io.File;  
 import java.io.FileInputStream;  
 import java.io.IOException;  
 import java.security.MessageDigest;  
   
 /** 
  * MD5加密工具类 
  */  
public class MD5EncoderUtil {  
     private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",  
             "e", "f" };  
     private static String algorithm = "MD5";  
   
     public static String encode(String salt, String rawPass) {  
         return encode(rawPass.toLowerCase() + salt);  
    }  
   
     public static String encode(String rawPass) {  
         String result = null;  
         try {  
             MessageDigest md5 = MessageDigest.getInstance(algorithm);  
             // 加密后的字符串  
             result = byteArrayToHexString(md5.digest(rawPass.getBytes("utf-8")));  
        } catch (Exception ex) {  
             ex.printStackTrace();  
         }  
         return result;  
     }  
   
     public static String getFileMd5(File file) {  
         FileInputStream fileInputStream = null;  
         try {  
             MessageDigest md5 = MessageDigest.getInstance(algorithm);  
             fileInputStream = new FileInputStream(file);  
             byte[] buffer = new byte[8192];  
            int length;  
             while ((length = fileInputStream.read(buffer)) != -1) {  
                md5.update(buffer, 0, length);  
            }  
            return byteArrayToHexString(md5.digest());  
         } catch (Exception e) {  
             e.printStackTrace();  
             return null;  
         } finally {  
            if (fileInputStream != null) {  
                 try {  
                     fileInputStream.close();  
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
             }  
   
        }  
  
     }  
   
     public static boolean isPasswordValid(String encPass, String loginName, String rawPass) {  
         String pass1 = encPass;  
         String pass2 = encode(loginName, rawPass);  
         return pass1.toUpperCase().equals(pass2.toUpperCase());  
     }  
   
     /* 
      * 转换字节数组为16进制字串 
      */  
     private static String byteArrayToHexString(byte[] b) {  
         StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++) {  
            resultSb.append(byteToHexString(b[i]));  
        }  
         return resultSb.toString();  
     }  
   
    private static String byteToHexString(byte b) {  
        int n = b;  
        if (n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
} 


