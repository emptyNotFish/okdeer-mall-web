package com.okdeer.mall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期格式转换工具类
 * @author Administrator
 *
 */
public class DateFormatUtil
{
    /**
     * 24小时制格式
     */
    public final static String FORMAT_TIME_24 = "yyyy-MM-dd HH:mm:ss";
   
    /**
     * 12小时制格式
     */
    public final static String FORMAT_TIME_12 = "yyyy-MM-dd hh:mm:ss";
     
    /**
     * 转换日期格式
     * @param format 格式
     * @param date 日期
     * @return formatDate 格式化日期
     */
    public static String getFormatDate(String format,String date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formatDate = sdf.format(date);
        return formatDate;
    }
   
    /**
     * 转换日期格式
     * @param format 格式
     * @param date 日期
     * @return formatDate 格式化日期
     */
    public static String getFormatDate(String format,Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formatDate = sdf.format(date);
        return formatDate;
    }
}

