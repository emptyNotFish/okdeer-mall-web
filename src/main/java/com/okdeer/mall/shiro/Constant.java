package com.okdeer.mall.shiro;

/**
 * @pr yscm
 * @desc 公共常量类
 * @author lijun
 * @date 2015年7月30日 下午3:29:11
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *		重构V4.1			2016-08-19			wushp				获取短信增加验证码
 */
public class Constant {
	
	/**
	 * 
	 */
	private Constant(){}
	
	/**
	 * SESSION USER KEY
	 */
	public static final String SESSION_USER = "session_user";
	
	/**
	 * @Fields CURR_USER_ID : 当前登录用户id字段
	 */
	//add by mengsj begin
	public static final String CURR_USER_ID = "currentUserId";
	//add by mengsj end
	
	/**
	 * 数据字典房子归属类型 add by zhaoqc
	 */
	public static final String ROOM_BELONG_TYPE = "HOUSE_BELONG_TYPE";
	
	/**
	 * 数据字典单元类型 add by zhaoqc
	 */
	public static final String CELL_TYPE = "cellType";
	
	/**
	 * 导入时默认的业主手机号码11个8 add by zhaoqc
	 */
	public static final String DEFAULT_MOBILE = "88888888888";
	
	/**
	 * 系统编码，物业系统mall
	 */
	public static final String SYSTEM_CODE = "mall";
	
	/**
	 * 用户中心url地址
	 */
	public static final String YSC_CA_URL = "YSCCA_URL";
	
	/**
	 * 物业公司管理员角色 主键ID（运营商授权） 
	 */
	public static final String MANAGER_ROLE_ID_WYGS = "71A65149381211E5B131989096CAB01F"; 
	
	/**
	 * 代理商管理员角色 主键ID（运营商授权）  
	 */
	public static final String MANAGER_ROLE_ID_AGENT = "9F333CA8381211E5B131989096CAB01F";
	
	/**
	 * 运营商管理员角色 主键ID
	 */
	public static final String MANAGER_ROLE_ID_YYS = "8D403414374E11E5A4C6989096CAA445";
	
	/**
	 * 小区管理处管理员角色 主键ID
	 */
	public static final String MANAGER_ROLE_ID_XQGLC = "8A3FDAB939C111E5B959989096AB8833";
	
	/**
	 * 超级管理员角色 主键ID
	 */
	public static final String MANAGER_ROLE_ID_SUPERADMIN = "14BEF1F039B511E59B41989096CAA445";
	
	
	/**
	 * 用户中心返回状态标识  
	 */
	public static final String CA_STATUS = "status";
	
	/**
	 * 用户中心返回业务错误码  
	 */
	public static final String CA_ERRCODE = "errCode";
	
	/**
	 * 用户中心返回成功状态标识 
	 */
	public static final String CA_STATUS_SUCCESS = "success";
	
	/**
	 * 用户中心返回失败状态标识 
	 */
	public static final String CA_STATUS_FAILURE = "failure";
	
	/**
	 * token_key 用户登录状态唯一标识
	 */
	public final static String DEFAULT_TOKEN_KEY = "token";
	
	/**
	 * machineCode
	 */
	public final static String MACHINE_CODE = "machineCode";
	
	public final static String CLIENT_TYPE = "clientType";
	
	public final static String DEERS = "deers";
	
	public final static String DES_KEY = "okdeerok";
	
	/************ excel 导出 ************/
	/**
	 * 导出限制最大条数
	 */
	public static final Integer MAX_EXPORT_NUM = 2000;
	
	/**
	 * 导出限制最大条数
	 */
	public static final Integer MAX_EXPORT_NUM2 = 10000;
	
	/**
	 * 允许导出标示
	 */
	public static final String ALLOW_EXPORT = "allowExport";
	
	/**
	 * 不允许导出标示
	 */
	public static final String NOT_ALLOW_EXPORT = "notAllowExport";
	
	/**
	 * 普通店铺服务保障信息 ，数据来源数据字典，type名
	 */
	public static final String STORE_SERVICE_GUARANTEED = "STORE_SERVICE_GUARANTEED";
	
	/**
	 * 团购店铺服务保障信息 ，数据来源数据字典，type名
	 */
	public static final String TG_STORE_SERVICE_GUARANTEED = "TG_STORE_SERVICE_GUARANTEED";
	
	
	/************start:add by wushoupu************/
	/**
	 * 代理商编号生成的自增序号长度
	 */
	public static final int AGENT_CODE_LENGTH = 2;
	/**
	 * 物业公司编号生成的自增序号长度
	 */
	public static final int PROPERTY_COMPANY_CODE_LENGTH = 3;
	/**
	 * 小区编号生成的自增序号长度
	 */
	public static final int SMALL_COMMUNITY_CODE_LENGTH = 5;
	/**
	 * 代理商编号类型
	 */
	public static final String AGENT_CODE_TYPE = "D";
	/**
	 * 物业公司编号类型
	 */
	public static final String PROPERTY_COMPANY_CODE_TYPE = "W";
	/**
	 * 小区编号类型
	 */
	public static final String SMALL_COMMUNITY_CODE_TYPE = "X";
	/**
	 * 生成编号的连接符
	 */
	public static final String CODE_CONNECT_STRING = "-";
	/************end:add by wushoupu************/
	
	/**
	 * 迭代次数(SHA1加密)
	 */
	public static final int HASH_INTERATIONS = 1024;
	
	/**
	 * 盐长度(SHA1加密)
	 */
	public static final int SALT_SIZE = 8;
	
	/** 0-10的常量*/
	/**
	 * 0
	 */
	public static final int ZERO = 0;
	/**
	 * 1
	 */
	public static final int ONE = 1;
	/**
	 * 2
	 */
	public static final int TWO = 2;
	/**
	 * 3
	 */
	public static final int THREE = 3;
	/**
	 * 4
	 */
	public static final int FOUR = 4;
	/**
	 * 5
	 */
	public static final int FIVE = 5;
	/**
	 * 6
	 */
	public static final int SIX = 6;
	/**
	 * 7
	 */
	public static final int SEVEN = 7;
	/**
	 * 8
	 */
	public static final int EIGHT = 8;
	/**
	 * 9
	 */
	public static final int NINE = 9;
	/**
	 * 10
	 */
	public static final int TEN = 10;
	/**
	 * 30
	 */
	public static final int THIRTH = 30;
	
	/**
	 * 总部门店编号
	 */
	public static final String YBL_STORE_NO = "00000";
	
	/**
	 * 称重商品条码22开头 
	 */
	public static final String WEIGHT_SKU_CODE_PREFIX = "22";
	
	/**
     * 计件商品条码21开头 
     */
	public static final String PIECE_SKU_CODE_PREFIX = "21";  
	
	/**
	 * 999
	 */
	public static final int DEFAULT_TRADE_MAX = 999;
	
	// begin 重构4.1 add by wushp 20160819
	/**
	 * 验证码sessionKey
	 */
	public final static String SESSION_VERIFYCODE ="verifyCode";
	// end 重构4.1 add by wushp 20160819

	// 登录零售系统对应的角色编码  add by lijun begin
	/**
	 * (A)进销存自营店管理员角色
	 */
	public final static String JXC_STORE_SELF_ROLE = "jxc_store_self_role";
	/**
	 * (A)进销存自营店店长
	 */
	public final static String JXC_STORE_SELF_SHOPKEEPER = "jxc_store_self_shopkeeper";
	
	/**
	 * (A)进销存自营店店员
	 */
	public final static String JXC_STORE_SELF_SELLER = "jxc_store_self_seller";
	
	/**
	 * (BC)进销存加盟店管理员角色
	 */
	public final static String JXC_STORE_FRANCHISE_ROLE = "jxc_store_franchise_role";
	
	/**
	 * (BC)进销存加盟店店长
	 */
	public final static String JXC_STORE_FRANCHISE_SHOPKEEPER = "jxc_store_franchise_shopkeeper";
	
	/**
	 * (BC)进销存加盟店店员
	 */
	public final static String JXC_STORE_FRANCHISE_SELLER = "jxc_store_franchise_seller";
	
	/**
	 * 机构和用户关系MQ信息发送TOPIC
	 */
	public final static String JXC_MQ_STORE_USER_TOPIC = "topic_branch";
	
	/**
	 * 机构和用户关系MQ信息发送TAG
	 */
	public final static String JXC_MQ_STORE_USER_TAG = "tag_user_add";
	// 登录零售系统对应的角色编码  add by lijun end
	
	/**  信息发布  */
	public class PublishInformation{
		
		/*** 消息通知  */
		public static final String INFO_TYPE_NOTICE = "1";
		/*** 消息公告  */
		public static final String INFO_TYPE_PUBLIC = "2";
		/*** 社区动态消息  */
		public static final String INFO_TYPE_COMMUNITY = "3";
		/*** 短信消息  */
		public static final String INFO_TYPE_SMS = "4";
		
		/*** 消息状态-未审核  */
		public static final String INFO_STATUS_UNAUDIT = "0";
		/*** 消息状态-已审核  */
		public static final String INFO_STATUS_AUDIT = "1";
		/*** 消息状态-已发送  */
		public static final String INFO_STATUS_SEND = "2";
		/*** 消息状态-驳回（审核不通过）  */
		public static final String INFO_STATUS_REJECT = "3";
		
		/*** 消息保存时间 -永久  */
		public static final int INFO_SAVE_TIME_ALWAYS = 0;
		/*** 消息保存时间 -30天  */
		public static final int INFO_SAVE_TIME_DAY_30 = 1;
		/*** 消息保存时间 -90天  */
		public static final int INFO_SAVE_TIME_DAY_90 = 2;
		
		/*** 小区目标类型-全部  */
		public static final String INFO_TARGET_TYPE_ALL = "0";
		/*** 小区目标类型-物业  */
		public static final String INFO_TARGET_TYPE_PROPERTY = "1";
		/*** 小区目标类型-代理商  */
		public static final String INFO_TARGET_TYPE_AGENT = "2";
		/*** 小区目标类型-小区  */
		public static final String INFO_TARGET_TYPE_COMMUNITY = "3";
		/*** 小区目标类型-小区管理处  */
		public static final String INFO_TARGET_TYPE_MANAGER = "4";
		/*** 小区目标类型-楼栋  */
		public static final String INFO_TARGET_TYPE_BUILDING = "5";
		/*** 小区目标类型-楼层  */
		public static final String INFO_TARGET_TYPE_FLOOR = "6";
		/*** 小区目标类型-房间  */
		public static final String INFO_TARGET_TYPE_ROOM = "7";
		
	}
}
