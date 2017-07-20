package com.okdeer.mall.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
/**
 * 将xml转换为map
 * @author Administrator
 * @date 20170719
 */
public class Xml2Map {
	   
    @SuppressWarnings("unchecked")
    public static Map<String,Object> buildXmlBody2map(Element body) {
        Map<String,Object> vo = new HashMap<String,Object>();
        if (body != null) {
            List<Element> elements = body.elements();
            for (Element element : elements) {
                String key = element.selectSingleNode("UUID").getText();
                if (StringUtils.hasText(key)) {
                    vo.put(key, element);
                }
            }
        }
        return vo;
    }
}
