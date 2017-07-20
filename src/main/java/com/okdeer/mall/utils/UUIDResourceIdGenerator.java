package com.okdeer.mall.utils;

import java.util.UUID;

import org.springframework.util.StringUtils;


/**
 * 生成uuid并去除-
 * @author Administrator
 *
 */
public class UUIDResourceIdGenerator {
    private static final String separator = "-";
    
    public String getResourceId() {
        return StringUtils.remove(UUID.randomUUID().toString(), separator);
    }
}

