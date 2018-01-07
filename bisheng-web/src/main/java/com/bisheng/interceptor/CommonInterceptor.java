package com.bisheng.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuchao on 15/12/12.
 *
 * 拦截器
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


//        String username =  (String)request.getSession().getAttribute("userName");
//        if(username == null){
//            log.info("Interceptor：跳转到login页面！");
//            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
//            return false;
//        }
//        UserInfo userInfo = (UserInfo) jedisUtil.get(username);
//        if(userInfo == null){
//            log.info("Interceptor：跳转到login页面！");
//            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
//            return false;
//        }else
            return true;
    }

}
