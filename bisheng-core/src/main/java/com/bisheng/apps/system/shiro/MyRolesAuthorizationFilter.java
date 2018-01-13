package com.bisheng.apps.system.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @ClassName: MyFormAuthenticationFilter
 * @author: shihao.li
 * @Explain: 自定义shiro的authc,roles过滤
 * @date: 2018年01月13日 下午2:25:39
 */
public class MyRolesAuthorizationFilter extends RolesAuthorizationFilter {
    /**
     * 重写方法, 自定义roles类过滤，包含hasRole标签和注解
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws IOException
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        // 没有指定角色，因此无需检查-允许访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }

        for (int i = 0; i < rolesArray.length; i++) {
            if (subject.hasRole(rolesArray[i])) {
                return true;
            }
        }
        return false;
    }
}
