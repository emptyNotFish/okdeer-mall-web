package com.okdeer.mall.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * json工具类
 * @author Administrator
 *
 */
public final class JsonUtils
{
    private static final Logger logger =
        LoggerFactory.getLogger(JsonUtils.class);
   
    private static ObjectMapper objectMapper = new ObjectMapper();
   
   public static String toJSONString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            return null;
        }
    }

    public static String toJson(Object value)
    {
        try
        {
            return objectMapper.writeValueAsString(value);
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            logger.error(localException.getMessage(), localException);
        }
        return null;
    }
   
    public static void toJson(HttpServletResponse response, String contentType,
        Object value)
    {
        Assert.notNull(response);
        Assert.hasText(contentType);
        try
        {
            response.setContentType(contentType);
            objectMapper.writeValue(response.getWriter(), value);
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            logger.error(localException.getMessage(), localException);
        }
    }
   
    @SuppressWarnings("rawtypes")
    public static Map jsonToMap(String json) {
        Map maps = null;
        try {
            if (json.indexOf("[") == 0) {
                json = json.substring(1).substring(0, json.length() - 2);
            }
            maps = objectMapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
   
    public static void toJson(HttpServletResponse response, Object value)
    {
        Assert.notNull(response);
        PrintWriter localPrintWriter = null;
        try
        {
            localPrintWriter = response.getWriter();
            objectMapper.writeValue(localPrintWriter, value);
            localPrintWriter.flush();
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            logger.error(localException.getMessage(), localException);
        }
        finally
        {
            IOUtils.closeQuietly(localPrintWriter);
        }
    }
   
    public static <T> T toObject(String json, Class<T> valueType)
    {
        Assert.hasText(json);
        Assert.notNull(valueType);
        try
        {
            return objectMapper.readValue(json, valueType);
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            logger.error(localException.getMessage(), localException);
        }
        return null;
    }
   
    public static <T> T toObject(String json, TypeReference<?> typeReference)
    {
        Assert.hasText(json);
        Assert.notNull(typeReference);
        try
        {
            return objectMapper.readValue(json, typeReference);
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            logger.error(localException.getMessage(), localException);
        }
        return null;
    }
   
    public static <T> T toObject(String json, JavaType javaType)
    {
        Assert.hasText(json);
        Assert.notNull(javaType);
        try
        {
            return objectMapper.readValue(json, javaType);
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            logger.error(localException.getMessage(), localException);
        }
        return null;
    }
   
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map convertBean(Object bean)
                throws Exception {
            Class type = bean.getClass();
            Map returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
            return returnMap;
      }
    
        /**
         * 从json字符处中获取对应key的value
         * @param json  json字符串
         * @param key   
         * @return
         */
        public  static String getFieldValue(String json,String key){
            try
            {
                json = json.replaceAll("'", "\"");
                JsonNode jn = new ObjectMapper().readTree(json);
                String value = jn.get(key).toString();
                value = value.replaceAll("\"", "");
                return value ;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
}

