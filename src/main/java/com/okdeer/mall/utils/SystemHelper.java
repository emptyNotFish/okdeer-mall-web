package com.okdeer.mall.utils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * 系统工具类 获取服务器地址IP等信息
 * @author Administrator
 *
 */
public class SystemHelper
{
    /** log日志对象 */
    private static transient Logger log = Logger.getLogger(SystemHelper.class);
   
    /**获得系统属性集   */
    public static Properties props = System.getProperties();
   
    /**操作系统名称 */
    public static String OS_NAME = getPropertery("os.name");
   
    /**
     *
     * 根据系统的类型获取本服务器的ip地址
     *
     * InetAddress inet = InetAddress.getLocalHost();
     * 但是上述代码在Linux下返回127.0.0.1。
     * 主要是在linux下返回的是/etc/hosts中配置的localhost的ip地址，
     * 而不是网卡的绑定地址。后来改用网卡的绑定地址，可以取到本机的ip地址：）：
     * @throws UnknownHostException
     */
    public static InetAddress getSystemLocalIp()
    {
        InetAddress inet = null;
        String osname = getSystemOSName();
        try
        {
            //区别window系统和linux系统
            if (osname.toUpperCase().indexOf("WINDOWS") != -1)
            {
                inet = getWinLocalIp();
            }
            else if (osname.toUpperCase().indexOf("LINUX") != -1)
            {
                inet = getUnixLocalIp();
            }
            if (null == inet)
            {
                log.error("主机的ip地址未知");
            }
        }
        catch (SocketException e)
        {
            log.error("获取本机ip错误" + e.getMessage());
        }
        return inet;
    }
   
    /**
     * 获取操作系统名称
     * @return
     */
    public static String getSystemOSName()
    {
        //获得系统属性集  
        Properties props = System.getProperties();
        //操作系统名称
        String osname = props.getProperty("os.name");
        if (log.isDebugEnabled())
        {
            log.info("the ftp client system os Name " + osname);
        }
        return osname;
    }
   
    /**
     * 获取属性的值
     * @param propertyName
     * @return
     */
    public static String getPropertery(String propertyName)
    {
        return props.getProperty(propertyName);
    }
   
    /**
     * 获取window 本地ip地址
     * @return
     * @throws UnknownHostException
     */
    private static InetAddress getWinLocalIp()
    {
        InetAddress inet=null;
        try
        {
            inet = InetAddress.getLocalHost();
        }
        catch (UnknownHostException e)
        {
            log.error("获取Windows本地IP地址出现异常，异常信息："+e.getMessage());
        }
        return inet;
    }
   
    /**
     *
     * 可能多多个ip地址只获取一个ip地址
     * 获取Linux 本地IP地址
     * @return
     * @throws SocketException
     */
    private static InetAddress getUnixLocalIp() throws SocketException
    {
       try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        return ia;
                    }
                    break;
                }
            }
        } catch (SocketException e) {
            log.error("获取LINUX平台下的IP地址，出现异常，异常信息："+e.getMessage());
        }
        return null;
    }
   
    public String getLocalIP() {
        String ip = "";
        try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return ip;
    }
   
    /**
     *
     * 获取当前运行程序的内存信息
     * @return
     */
    public static final String getRAMinfo()
    {
        Runtime rt = Runtime.getRuntime();
        return "RAM: " + rt.totalMemory() + " bytes total, " + rt.freeMemory()
            + " bytes free.";
    }
}
