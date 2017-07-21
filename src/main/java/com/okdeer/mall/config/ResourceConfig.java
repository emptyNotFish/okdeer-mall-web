package com.okdeer.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:/META-INF/spring-dubbo.xml" })
public class ResourceConfig {

}