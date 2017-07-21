package com.okdeer.mall.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@ImportResource({ "classpath:/META-INF/spring-shiro-config.xml" })
public class ShiroConfiguration {

//	private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();


//	@Value("${casServerUrlPrefix}")
//	private String casServerUrlPrefix;
//	private String casServerUrlPrefix = "http://10.20.146.26:8080";

//	@Value("${serviceUrl}")
//	private String serviceUrl;
//	private String serviceUrl = "http://127.0.0.1:8080/yscmall";
	
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

//	@Bean(name="userRealm")
//	public UserRealm getUserRealm() {
//		UserRealm realm = new UserRealm();
//		realm.setCachingEnabled(true);
//		realm.setCasServerUrlPrefix(casProperties.getCasServerUrlPrefix());
//		realm.setCasService(casProperties.getServiceUrl() + "/cas");
//		return realm;
//	}
//
//	@Bean(name = "shiroEhcacheManager")
//	public EhCacheManager getEhCacheManager() {
//		EhCacheManager em = new EhCacheManager();
//		em.setCacheManagerConfigFile("classpath:META-INF/cache/ehcache-shiro.xml");
//		return em;
//	}
//
//	@Bean(name = "lifecycleBeanPostProcessor")
//	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//
////	@Bean
////	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
////		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
////		daap.setProxyTargetClass(true);
////		return daap;
////	}
//
//	@Bean(name = "securityManager")
//	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
//		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//		dwsm.setRealm(getUserRealm());
//		dwsm.setCacheManager(getEhCacheManager());
//		dwsm.setSubjectFactory(new CasSubjectFactory());
//		return dwsm;
//	}
//
//	@Bean
//	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
//		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//		aasa.setSecurityManager(getDefaultWebSecurityManager());
//		return new AuthorizationAttributeSourceAdvisor();
//	}
//
//	@Bean(name = "shiroFilter")
//	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
//		shiroFilterFactoryBean.setLoginUrl(getLoginUrl());
//		// shiroFilterFactoryBean.setSuccessUrl("/sa/index");
//
//		Map<String, Filter> filters = Maps.newHashMap();
//		filters.put("authc", new FormAuthenticationCaptchaFilter());
//		filters.put("casFilter", getCasFilter());
//		shiroFilterFactoryBean.setFilters(filters);
//		filterChainDefinitionMap.put("/health", "anon");
//		filterChainDefinitionMap.put("/static/**", "anon");
//		filterChainDefinitionMap.put("/icon/**", "anon");
//		filterChainDefinitionMap.put("/js/**", "anon");
//		filterChainDefinitionMap.put("/css/**", "anon");
//		filterChainDefinitionMap.put("/lang/**", "anon");
//		filterChainDefinitionMap.put("/easyui/**", "anon");
//		filterChainDefinitionMap.put("/my97/**", "anon");
//		filterChainDefinitionMap.put("/plugins/**", "anon");
//		filterChainDefinitionMap.put("/cas", "casFilter");
//		filterChainDefinitionMap.put("/logout", "logout");
//		filterChainDefinitionMap.put("/**", "authc");
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		return shiroFilterFactoryBean;
//	}
//
//	/**
//	 * @Description: TODO
//	 * @return   
//	 * @author guocp
//	 * @date 2017年1月7日
//	 */
//	private Filter getCasFilter() {
//		CasFilter casFilter = new CasFilter();
//		casFilter.setFailureUrl("/common/403.jsp");
//		return casFilter;
//	}
//
//
//	private String getLoginUrl() {
//		return casProperties.getCasServerUrlPrefix() + "/login?service=" + casProperties.getServiceUrl() + "/cas";
//	}

}
