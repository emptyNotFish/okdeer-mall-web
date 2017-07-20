package com.okdeer.mall.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
    private static final String regix = "^[\\d\\w\\D\\W]{30,50}(\n)";
    /**
     * 将list集合中的元素根据指定分隔符转换成字符串
     * @param list：字符串集合
     * @param separator：分隔符
     * @return ：字符串
     */
    public static String listToString(List<String> list, String separator)
    {
        if (list == null || list.isEmpty() || separator==null)
        {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (String str : list)
        {
            buffer.append(str + separator);
        }
        return buffer.substring(0, buffer.lastIndexOf(separator));
    }
   
    /**
     * 判断字符串是否为空
     *
     * @param obj
     * @return null?true:flase
     */
    public static boolean isNULL(Object obj)
    {
        if (null == obj || "".equals(obj))
            return true;
        return false;
    }
   
    /**
     * 去掉license的xml标识
     * @param license
     * @return
     */
    public static String licenseStr(String license)
    {
        
        Matcher mat = Pattern.compile(regix).matcher(license);
        if (mat.find())
        {
            return license.replace(mat.group(), "");
        }
        return license;
    }
   
   
}

