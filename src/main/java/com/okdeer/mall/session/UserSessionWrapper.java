package com.okdeer.mall.session;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.okdeer.base.common.utils.UuidUtils;
import com.okdeer.base.redis.IRedisTemplateWrapper;

/**
 * ClassName: UserSessionWrapper 
 * 用户会话包装器
 * @author guocp
 * @date 2017年6月20日
 *
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *
 */
@Component
public class UserSessionWrapper {

	/**
	 * 过期时间（天）
	 */
	@Value("${session.expire}")
	private Long expire;

	/**
	 * redis Wrapper
	 */
	@Resource
	private IRedisTemplateWrapper<String, UserSession> redisTemplateWrapper;

	public UserSession getUserSession(String token) {
		return this.redisTemplateWrapper.get(token);
	}

	/**
	 * 创建用户会话
	 * @param userId
	 * @param storeId
	 * @return   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public String createUserSession(String userId) {
		String sessionId = UuidUtils.getUuid();
		redisTemplateWrapper.set(sessionId, new UserSession(sessionId, userId), expire * 24 * 60 * 60,
				TimeUnit.SECONDS);
		return sessionId;
	}
	/**
	 * 创建用户会话
	 * @param userId
	 * @param storeId
	 * @return   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public String createUserSession(String sessionId,String userId) {
		redisTemplateWrapper.set(sessionId, new UserSession(sessionId, userId), expire * 24 * 60 * 60,
				TimeUnit.SECONDS);
		return sessionId;
	}

	/**
	 * 删除用户会话
	 * @param token   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public void delSession(String token) {
		this.redisTemplateWrapper.del(token);
	}
	
	
	/**
	 * 更新会话
	 * @param token
	 * @param session   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public void updateSession(String token , UserSession session ){
		redisTemplateWrapper.set(token, session);
	}

	/**
	 * 刷新用户会话
	 * @param session   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public void refreshSession(UserSession session) {
		session.updateState();
		this.redisTemplateWrapper.set(session.getId(), session, expire * 24 * 60 * 60, TimeUnit.SECONDS);
	}

	/**
	 * 设置会话过期
	 * @param oldToken   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public void expireSession(String token) {
		try{
			//清除已上线设备
			UserSession session = getUserSession(token);
			if (session != null) {
				//标记令牌失效
				session.setExpire();
				this.updateSession(token, session);
			}
		}catch(Exception e){
			this.delSession(token);
		}
	}

}
