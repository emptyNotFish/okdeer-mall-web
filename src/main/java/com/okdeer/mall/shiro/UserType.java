package com.okdeer.mall.shiro;


import org.apache.commons.lang.StringUtils;

import com.okdeer.base.common.enums.ViewEnum;

/**
 * 
 * @pr yscm
 * @desc 用户类型枚举
 * @author yangq
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 */
public enum UserType implements ViewEnum {

	/**
	 * 商城后台管理员
	 */
	mallSuperAdmin("10000"),

	/**
	 * 运营商
	 */
	operateCode("10001"),

	/**
	 * 代理商
	 */
	agentCode("10002"),

	/**
	 * 买家
	 */
	buyerCode("10003"),

	/**
	 * 卖家
	 */
	sellerCode("10004"),

	/**
	 * 店长
	 */
	storeKeeper("10005"),

	/**
	 * 店员
	 */
	storeAssistant("10006"),
	/**
	 * 进销存后台管理员
	 */
	imsSuperAdmin("10007"),

	/**
	 * 运营商
	 */
	operatorCode("10001");

	/**
	 * 枚举值
	 */
	private String value;

	UserType(String value) {
		this.value = value;
	}

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public String getValue() {
		return this.value;
	}

	/**
	 * 根据值取枚举
	 * 
	 * @param value
	 *            枚举值
	 * @return 枚举对象
	 */
	public static UserType enumValueOf(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		for (UserType userType : values()) {
			if (value.equalsIgnoreCase(userType.getValue())) {
				return userType;
			}
		}
		return null;
	}

}
