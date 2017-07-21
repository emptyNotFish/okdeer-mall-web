package com.okdeer.mall.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: InitBeanConfig 
 * @Description:   初始化文件上传对象配置
 * @author xuzq
 * @date 2017年5月24日
 *
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *		V2.3			2017年5月24日			xuzq		               初始化文件上传对象配置
 */
@Configuration
public class InitBeanConfig {

	@Bean   
    public MultipartConfigElement multipartConfigElement() {   
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;  
        factory.setMaxFileSize("10MB"); //KB,MB 
        /// 设置总上传数据总大小 
        factory.setMaxRequestSize("20MB");   
        return factory.createMultipartConfig();   
	} 
	
}
