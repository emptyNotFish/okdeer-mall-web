package com.okdeer.mall.shiro;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.okdeer.ca.api.common.SysMenuPermissionDto;
import com.okdeer.ca.api.common.SystemUserDto;
import com.okdeer.ca.api.common.enums.SystemCodeEnum;
import com.okdeer.ca.api.sysuser.service.ISysUserApi;

/**
 * 用户登录授权service(shrioRealm)
 * 
 * @author ty
 * @date 2015年1月14日
 */
public class UserRealm extends CasRealm {

	private final static Logger log = LoggerFactory.getLogger(UserRealm.class);

	/**
	 * systemSSOApi用户中心的接口,用来加载菜单和权限
	 */
	@Autowired
	private ISysUserApi sysUserApi;
	
//	protected ISysUserApi getSysUserApi(){
//		if(sysUserApi==null){
//			sysUserApi = BeanFactoryHelper.getBeanfactory().getBean(ISysUserApi.class);
//		}
//		return sysUserApi;
//	}

	// @Resource
	// private StoreMemberRelationService storeMemberRelationService;

	/**
	 * @param token
	 *            登入令牌
	 * @return info 认证信息
	 * @throws AuthenticationException
	 *             认证异常
	 * @desc 登入认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		try {
			AuthenticationInfo authInfo = super.doGetAuthenticationInfo(token);

			String sysUserId = (String) token.getPrincipal();
			// Begin 支持运营商多角色登陆 added by tangy 2016-9-1
			SystemUserDto systemUserDto = sysUserApi.findUserTypeByUserId(sysUserId, UserType.operatorCode.getValue());
			// End added by tangy
			if (null == systemUserDto || null == systemUserDto.getUserRoleType()) {
				// Begin 支持代理商多角色登陆 added by tangy 2016-9-1
				systemUserDto = sysUserApi.findUserTypeByUserId(sysUserId, UserType.agentCode.getValue());
				// End added by tangy
			}
			if (null == systemUserDto) {
				throw new Exception("登录查询用户信息为空!");
			}

			SysUser sysUser = new SysUser();
			PropertyUtils.copyProperties(sysUser, systemUserDto);
			sysUser.setUserType(systemUserDto.getUserRoleType());

			// 设置用户session
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute(Constant.SESSION_USER, sysUser);

			// SysUser user =
			// (SysUser)session.getAttribute(Constant.SESSION_USER);
			return authInfo;
		} catch (Exception e) {
			log.error("登入认证查询当前用户信息异常", e);
			throw new AuthenticationException(e);
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SysUser sysUser = UserUtil.getCurrentUser();
		String sysUserId = sysUser.getId();
		// 把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(sysUserId, SecurityUtils.getSubject().getPrincipals());

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		try {
			List<SysMenuPermissionDto> sysMPList = sysUserApi.findPermissionBySysUserId(sysUserId, SystemCodeEnum.MALL);
			if (null == sysMPList || sysMPList.isEmpty()) {
				return null;
			}
			for (SysMenuPermissionDto sysMP : sysMPList) {
				info.addStringPermissions(sysMP.getPermissions(null));
			}
		} catch (Exception e) {
			log.debug("查询用户的权限异常", e);
			return null;
		}
		return info;
	}

}
