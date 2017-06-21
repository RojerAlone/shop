package cn.cie.interceptor;

import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RojerAlone on 2017/6/21.
 * 管理员权限拦截，如果用户不是管理员，那么跳转到管理员的登陆页面
 */
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserHolder userHolder;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/admin/login")) {
            return true;
        }
        if (userHolder.getUser() != null && userHolder.getUser().getUsername().equals("admin")) {
            return true;
        }
        response.sendRedirect("/admin/login");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
