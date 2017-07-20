package com.okdeer.mall.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取网络地址和ip
 * @author Administrator
 *
 */
public class Networks {
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }
    public static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }
}
