package com.okdeer.mall.utils;

import java.util.UUID;
/**
 * 获取guid的字节数组
 * @author Administrator
 *
 */
public class GuidUtils {
    public static String getGuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static byte[] getGuidByte(){
        return ByteToHex.hex2byte(GuidUtils.getGuid());
    }
}

