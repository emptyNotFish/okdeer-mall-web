package com.okdeer.mall.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * json转换成字符串和map工具类
 * @author Administrator
 *
 */
public class JsonHelper {
    
    public  static String convertObjecToJsonStr(Object o){
        String res = "" ;
        try
        {
            res = new ObjectMapper().writeValueAsString(o);
            if(res.startsWith("\"") && res.endsWith("\"")){
                res = res.substring(1, res.length()-1);
            }
            res = res.replaceAll("\\\\\"", "\"");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
 
    }
   
    public  static String getFieldValue(String json,String key){
        try
        {
            json = json.replaceAll("'", "\"");
            JsonNode jn = new ObjectMapper().readTree(json);
            return jn.get(key).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
 
    }
   
    public  static Map<String,String> convertJsonToMap(String json){
        Map<String, String> fieldMap = new HashMap<String, String>();
        try
        {
            if(json.startsWith("\"") && json.endsWith("\"")){
                json = json.substring(1, json.length()-1);
            }
            json = json.replaceAll("'", "\"");
            JsonNode jn = new ObjectMapper().readTree(json);
            Iterator<String> it = jn.getFieldNames();
            String key = null ;
            while (it.hasNext())
            {
                key = it.next() ;
                fieldMap.put(key, jn.get(key).getTextValue());
            }
            return fieldMap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fieldMap;
 
    }
   

