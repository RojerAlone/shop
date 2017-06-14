package cn.cie.interceptor;

import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RojerAlone on 2017/6/12.
 * 拦截器，某些功能需要登录才能继续，如果没有登录会被拦截，跳转到登录页
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserHolder userHolder;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("login拦截  ---------------------------------------------");
        if (userHolder.getUser() == null) {
            httpServletResponse.sendRedirect("/login");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
