package com.bisheng.apps.system.shiro;

import java.util.ArrayList;
import java.util.List;

import com.bisheng.apps.system.business.RoleBusiness;
import com.bisheng.core.common.util.MBeanUtil;
import com.bisheng.services.system.model.customized.RoleModel;
import com.bisheng.services.system.model.customized.UserModel;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.bisheng.apps.system.business.ResourceBusiness;
import com.bisheng.apps.system.business.UserBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.core.common.constant.Constants;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.enums.ResourceTypeEnum;
import com.bisheng.services.system.enums.UserStatusEnum;
import com.bisheng.services.system.model.customized.ResourceModel;
import com.bisheng.services.system.model.generated.User;

/** 
 * @ClassName: UserRealm 
 * @author: shihao.li
 * @Explain: 自定义Realm,重写登录认证和授权方法
 * @date: 2017年3月27日 下午4:19:04  
 */
public class UserRealm extends AuthorizingRealm{
	//创建日志对象
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
	
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private ResourceBusiness resourceBusiness;
	@Autowired
	private RoleBusiness roleBusiness;
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		
		if (userName == null) {
			userName = "";
		}
		User user;
		try {
			AuthParam param = new AuthParam();
			param.setUserName(userName);
			user = userBusiness.querySingleUserByExample(param);
		} catch (Exception e) {
			logger.error("【shiro登录认证】报错,错误原因:", e);
			throw new AuthenticationException("登录报错,请联系管理员");
		}
		//账号不存在
		if (user == null) {
			 throw new UnknownAccountException();
		}
		//判断用户状态
		if (user.getStatus().intValue() != UserStatusEnum.ACTIVE.getKey()) {
			throw new AccountException("用户非激活状态,不能使用");
		}
		
		logger.info("【shiro登录认证】当前用户为:"+ userName);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
				user.getPassword(), ByteSource.Util.bytes(Constants.SALT), getName());
		return authenticationInfo;
	}

	/**
	 * 授权，即权限验证
	 * 将按钮权限放入权限集合
	 * 将角色放入角色集合
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		List<ResourceModel> resourceList = null;
		List<RoleModel> roleModelList = null;
		try {
			//从principals获取主身份信息(真实身份信息)		认证时放入的是什么身份,授权时就要获取对应的身份
			User user = (User) principals.getPrimaryPrincipal();
			//根据用户id关联查询按钮
			AuthParam param = new AuthParam();
			param.setUserId(user.getUserId());
			param.setUserStatus(AvailStatusEnum.AVAILABLE.getKey());
			param.setRoleStatus(AvailStatusEnum.AVAILABLE.getKey());
			param.setResourceStatus(AvailStatusEnum.AVAILABLE.getKey());
			param.setResourceType(ResourceTypeEnum.BUTTON.getKey());
			resourceList = resourceBusiness.queryUserResourceByParam(param);

			roleModelList = roleBusiness.queryUserRoleByParam(param);
		} catch (Exception e) {
			logger.error("【shiro授权】根据当前用户,关联查询的可用的按钮资源报错,错误原因:", e);
		}
		
		logger.info("【shiro授权】根据当前用户,关联查询的可用的按钮资源为:"+ JSONObject.toJSONString(resourceList));
		//查到权限数据,返回授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		List<String> permissions = new ArrayList<>();
		if(resourceList != null && resourceList.size() > 0){
			for (ResourceModel resource : resourceList) {
				//将按钮的地址,放入权限集合,作为判断标志
				permissions.add(resource.getResourcePath());
			}
		}
		//把查询到的权限信息添加到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);

		if (null != roleModelList && !roleModelList.isEmpty()) {
			for(RoleModel role : roleModelList){
				//将角色名称,放入角色集合,作为判断标志
				simpleAuthorizationInfo.addRole(role.getRoleName());
			}
		}

		return simpleAuthorizationInfo;
	}
	
}
