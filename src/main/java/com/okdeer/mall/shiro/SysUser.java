package com.okdeer.mall.shiro;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.okdeer.base.common.enums.Disabled;
import com.okdeer.base.common.enums.WhetherEnum;

/**
 * @DESC: 
 * @author YSCGD
 * @date  2015-11-13 19:03:02
 * @version 1.0.0
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *		权限控制			2016-09-10		wushp				权限控制
 */
public class SysUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    private String id;

    /**
     * 编号
     */
    private Integer codeNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 登陆密码
     */
    private String loginPassword;
    
    /**
	 * 密码等级
	 */
	private Integer passwordLevel;

    /**
     * 父ID,指定当前用户的父ID
     */
    private String parentId;

    /**
     * 路径层级ID
     */
    private String pathIds;

    /**
     * 用户状态：0=禁用 1=启用
     */
    private Integer status;

    /**
     * 用户类型: 1=系统管理员,2=普通用户
     */
    private Integer type;
    
    /**
     * 用户角色类型
     */
    private UserType userType;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 座机
     */
    private String tel;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 描述
     */
    private String description;

    /**
     * 最后一次访问
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastVisit;

    /**
     * 系统来源
     */
    private String dataSource;

    /**
     * 默认进入系统
     */
    private String systemId;

    /**
     * 启用或禁用子账号: 0=禁用 1=启用
     */
    private Integer childEnabled;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 修改人ID
     */
    private String updateUserId;

    /**
     * 密码修改时间
     */
    private Date pwdUpdateTime;
    /**
     * 删除标识
     */
    private Disabled disabled;
    
    /**
     * 是否消息推送
     */
    private WhetherEnum isAccept;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(Integer codeNo) {
		this.codeNo = codeNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getPasswordLevel() {
		return passwordLevel;
	}

	public void setPasswordLevel(Integer passwordLevel) {
		this.passwordLevel = passwordLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPathIds() {
		return pathIds;
	}

	public void setPathIds(String pathIds) {
		this.pathIds = pathIds;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = UserType.enumValueOf(userType);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Integer getChildEnabled() {
		return childEnabled;
	}

	public void setChildEnabled(Integer childEnabled) {
		this.childEnabled = childEnabled;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getPwdUpdateTime() {
		return pwdUpdateTime;
	}

	public void setPwdUpdateTime(Date pwdUpdateTime) {
		this.pwdUpdateTime = pwdUpdateTime;
	}

	public Disabled getDisabled() {
		return disabled;
	}

	public void setDisabled(Disabled disabled) {
		this.disabled = disabled;
	}

	public WhetherEnum getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(WhetherEnum isAccept) {
		this.isAccept = isAccept;
	}
    
}