package com.okdeer.mall.utils;

import com.okdeer.mall.exception.CmsDownloadException;

public class MD5String {
	 // 十六进制字符串转二进制字节数组
    public byte[] hex2byte(String hexStr) throws CmsDownloadException
    {
        if (hexStr == null)
            return null;
        hexStr = hexStr.trim();
        int len = hexStr.length();
        if (len == 0 || len % 2 != 0)
            return null;
        byte[] result = new byte[len / 2];
        try
        {
            for (int i = 0; i < hexStr.length(); i += 2)
                result[i / 2] = (byte) Integer.decode("0x" + hexStr.substring(i, i + 2)).intValue();
            return result;
        }
        catch(Exception e)
        {
            LogParamUtils.errosMsg(logger, "hex2byte", functionModuleName,
                    e.getMessage() == null ? "java.lang.NullPointerException" : e.getMessage(),
                    e.getStackTrace());
            throw new CmsDownloadException(ResponseCode.DOWNLOAD_KMP_ERROR.getRepcode());
        }
    }
    // 二进制字节数组转十六进制
    public String byte2hex(byte[] b)
    {
        String result = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++)
        {
            stmp = java.lang.Integer.toHexString((b[i] & 0XFF));
            if (stmp.length() == 1)
                result = result + "0" + stmp;
            else
                result = result + stmp;
        }
        return result;
    }
    // 字符字节数组转十六进制
    public String getHexString(byte[] b) throws CmsDownloadException
    {
        String result = "";
        for (int i = 0; i < b.length; i++)
        {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
    public String MD5(String s) throws CmsDownloadException
    {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F' };
        try
        {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch(Exception e)
        {
             LogParamUtils.errosMsg(logger, "MD5", functionModuleName,
                     e.getMessage() == null ? "java.lang.NullPointerException" : e.getMessage(),
                     e.getStackTrace());
             throw new CmsDownloadException(ResponseCode.DOWNLOAD_KMP_ERROR.getRepcode());
        }
    }
   
    public String MD5(byte[] btInput) throws CmsDownloadException
    {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F' };
        try
        {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch(Exception e)
        {
             LogParamUtils.errosMsg(logger, "MD5", functionModuleName,
                     e.getMessage() == null ? "java.lang.NullPointerException" : e.getMessage(),
                     e.getStackTrace());
             throw new CmsDownloadException(ResponseCode.DOWNLOAD_KMP_ERROR.getRepcode());
        }
    }

}
