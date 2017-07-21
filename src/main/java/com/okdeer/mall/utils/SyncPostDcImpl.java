package com.okdeer.mall.utils;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.codec.http.HttpResponse;
import net.minidev.json.JSONObject;

/**
 * HttpClient请求和获取返回值流程
 * @author Administrator
 *
 */
public class SyncPostDcImpl  {
    Logger logger = LoggerFactory.getLogger(SyncPostDcImpl.class);
  
    @SuppressWarnings("unchecked")
    public void syncContentKey(String contentKey, String resourceUuid,
            Long keyId) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        HttpEntity entity = null;
        logger.info("*********************begin to sync key to dc*************************");
        logger.info("contentKey is " + contentKey + "contentId is " + resourceUuid);
        try {
            String url = ServletInit.globleIps.get("dc_inner").toString()
                    + DCUtils.SYNCCONTENTKEY;
            logger.info("request url is :" + url);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("contentKey", contentKey);
            jsonObj.put("contentId", resourceUuid);
            jsonObj.put("key_id", keyId);
            String paramJson = jsonObj.toJSONString();
            logger.info("request parameter is : " + paramJson);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            // 设置请求参数
            httpPost.setEntity(new StringEntity(paramJson, HTTP.UTF_8));
            // 发送请求
            response = httpClient.execute(httpPost);
            // 获取响应信息
            entity = response.getEntity();
            // 响应信息为json格式字符串
            String resultStr = EntityUtils.toString(entity, HTTP.UTF_8);
           
            logger.info("result from DCServer is : " + resultStr);
           
            ObjectMapper objectMapper = new ObjectMapper();
            Map jsonMap = objectMapper.readValue(resultStr, Map.class);
            String rtn = (String) jsonMap.get("rtn");
            if ("150000".equals(rtn)) {
                logger.info("同步contentKey,resourceUuid 到 DC 成功");
            } else {
                logger.error("同步contentKey,resourceUuid 到 DC 失败");
            }
        } catch (Exception e) {
            logger.error("同步contentKey,resourceUuid 到 DC 异常失败", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
        }
        logger.info("同步contentKey,resourceUuid 到 DC end");
    }
}

