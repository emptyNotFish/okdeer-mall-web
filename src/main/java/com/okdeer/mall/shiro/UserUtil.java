package com.okdeer.mall.shiro;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * 系统用户工具类 
 * @pr yschome-mall
 * @author guocp
 * @date 2016年2月2日 下午4:04:08
 */
public class UserUtil {

	/** 系统机器人用户 */
	private static SysUser robotUser = new SysUser();

	static {
		robotUser.setId("0");
		robotUser.setLoginName("robot");
		robotUser.setUserName("系统");
	}

	/**
	 * 获取当前用户session
	 * 
	 * @return session
	 */
	public static Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户httpsession
	 * @return httpsession
	 */
	public static Session getHttpSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户对象
	 * 
	 * @return user
	 */
	public static SysUser getCurrentUser() {
		Session session = SecurityUtils.getSubject().getSession();
		if (null != session) {
			return (SysUser) session.getAttribute(Constant.SESSION_USER);
		} else {
			return null;
		}
	}

	/**
	 * 获取当前用户相关对象
	 * @return user
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T getCurrentUserInfo(String key) {
		Session session = SecurityUtils.getSubject().getSession();
		if (null != session) {
			return (T) session.getAttribute(key);
		} else {
			return null;
		}
	}

	/**
	 * 获取系统机器人用户对象
	 * @return robotUser
	 */
	public static SysUser getRobotUser() {
		return robotUser;
	}

}
