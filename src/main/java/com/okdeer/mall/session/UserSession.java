package com.okdeer.mall.session;

import com.okdeer.base.common.model.SessionBean;

/**
 * 商家中心用户会话信息
 * ClassName: UserSession 
 * @author guocp
 * @date 2017年6月20日
 *
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *
 */
public class UserSession extends SessionBean {

	@SuppressWarnings("unused")
	private UserSession() {
	}

	public UserSession(String tokenId, String userId) {
		super(tokenId);
		this.setUserId(userId);
	}

	public void setUserId(final String userId) {
		this.setAttribute("userId", userId);
	}

	public String getUserId() {
		return (String) this.getAttribute("userId");
	}
	
	public void setExpire(){
		this.setAttribute("tokenState", 1);
	}

	/**
	 * 判断是否设置过期
	 * @return   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	public boolean isExpire() {
		Integer value = (Integer)this.getAttribute("tokenState");
		if (value == null) {
			return false;
		}
		return value == 1;
	}
}
