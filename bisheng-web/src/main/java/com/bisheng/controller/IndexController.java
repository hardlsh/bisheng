package com.bisheng.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.RedirectView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bisheng.apps.system.business.ResourceBusiness;
import com.bisheng.apps.system.param.AuthParam;
import com.bisheng.services.system.enums.AvailStatusEnum;
import com.bisheng.services.system.enums.ResourceTypeEnum;
import com.bisheng.services.system.model.generated.User;
import com.bisheng.services.system.vo.ResourceVo;
import com.bisheng.vo.ALMResponse;
import com.bisheng.vo.RetCode;

@Controller
@RequestMapping("index")
public class IndexController extends BaseController {
	// 定义日志对象
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ResourceBusiness resourceBusiness;

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, Model model) {

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		DefaultWebSessionManager shiroSessionManager = new DefaultWebSessionManager();
		long sessionStartTime = subject.getSession().getStartTimestamp().getTime();
		long lengthenTimeOut = subject.getSession().getTimeout();
		// 提前1秒去判断,防止if没进,等执行下面的时候它却失效了      user为null,是第一次进login方法,还没有登陆   
		if (((new Date().getTime() - sessionStartTime) >= lengthenTimeOut - 1000) ||
				user == null) {
			ThreadContext.remove(ThreadContext.SUBJECT_KEY);// 移除线程里面的subject
			shiroSessionManager.getSessionDAO().delete(subject.getSession());// 移除失效的session
			subject = SecurityUtils.getSubject();// 重新获取subject
		}

		logger.info("【登录】进入登录方法");
		if (user != null) {
			return toMain(request, model);
		}
		ModelAndView mav = new ModelAndView("login");
		ALMResponse res = null;
		// 如果登录失败,从request中获取认证异常信息,shiroLoginFailure就是shiro异常类的全名
		String exceptionClassName = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (exceptionClassName != null) {
			logger.info("【登录】登录异常, exceptionClassName is {}", exceptionClassName);
			String error = null;
			if (UnknownAccountException.class.getName().equalsIgnoreCase(exceptionClassName)) {
				error = "用户名/密码错误, 请重新输入";
			} else if (IncorrectCredentialsException.class.getName().equalsIgnoreCase(exceptionClassName)) {
				error = "用户名/密码错误, 请重新输入";
			} else if (AccountException.class.getName().equalsIgnoreCase(exceptionClassName)) {
				error = "用户状态不能访问, 请联系管理员";
			} else {
				error = "未知登录错误, 请联系管理员";
			}
			res = new ALMResponse(RetCode.FAILURE);
			res.setData(error);
			logger.info("【登陆】用户登录失败, 失败原因: " + error);
			request.setAttribute("result", res);
		}

		return mav;
	}

	@RequestMapping("/toLogin")
	public ModelAndView toLogin() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	// 进入主页,并查询菜单
	@RequestMapping("/toMain")
	public ModelAndView toMain(HttpServletRequest request, Model model) {
		logger.info("【登录】跳转至main页面,并加载菜单项");
		ModelAndView mav = new ModelAndView("main");
		try {
			// 从shiro的session中取activeUser
			Subject subject = SecurityUtils.getSubject();
			// 取身份信息(就是在自定义的realm中放入的Object类型的身份信息)
			User user = (User) subject.getPrincipal();
			
			if (user == null) {// session失效,跳转至登录页面
				return toLogin();
			}
			AuthParam param = new AuthParam();
			param.setUserId(user.getUserId());
			param.setUserStatus(AvailStatusEnum.AVAILABLE.getKey());
			param.setRoleStatus(AvailStatusEnum.AVAILABLE.getKey());
			param.setResourceStatus(AvailStatusEnum.AVAILABLE.getKey());
			param.setResourceType(ResourceTypeEnum.MENU.getKey());
			List<ResourceVo> voList = resourceBusiness.queryPackageMenuUserResources(param);

			logger.info("【登录】跳转至main页面,获取到的菜单:" + JSONObject.toJSONString(voList));
			model.addAttribute("menus", voList);
		} catch (Exception e) {
			logger.error("【登录】跳转至main页面错误,错误原因:", e);
		}
		return mav;
	}

	// 退出登录
	@RequestMapping("/logout")
	public void logout(ServletRequest request, ServletResponse response) {
		System.out.println("退出登录啦");
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			String redirectUrl = "/index/toLogin.do";
			RedirectView view = new RedirectView(redirectUrl, true, true);
			view.renderMergedOutputModel(null, (HttpServletRequest) request, (HttpServletResponse) response);
		} catch (Exception e) {
			logger.error("【登录】退出登录错误,错误原因:", e);
		}
	}

}
