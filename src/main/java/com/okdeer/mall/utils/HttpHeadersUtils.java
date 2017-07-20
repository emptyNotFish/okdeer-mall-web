package com.okdeer.mall.utils;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
/**
 * 设置httpclient请求头工具类
 * @author Administrator
 *
 */
public class HttpHeadersUtils {
    
    private static ResponseEntity<String> createResponseEntity(MediaType mediaType, String json, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new ResponseEntity<String>(json, headers, httpStatus);
    }
    
    
    public static MediaType setupMediaType(String type, String subtype, Charset charSet) {
        return new MediaType(type, subtype, charSet);
    }
    
    public static ResponseEntity<String> createDefaultResponseEntity(String json, HttpStatus httpStatus) {
        MediaType mediaType = setupMediaType("application", "json", Charset.forName("utf-8"));
        return createResponseEntity(mediaType, json, httpStatus);
    }
    
    
}

