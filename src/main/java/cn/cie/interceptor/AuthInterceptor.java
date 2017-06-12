package cn.cie.interceptor;

import cn.cie.entity.Token;
import cn.cie.entity.User;
import cn.cie.mapper.TokenMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by RojerAlone on 2017/6/11.
 * 用户身份验证
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserHolder userHolder;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = null;
        // 从请求中获取token
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        // 如果获取到了token
        if (token != null) {
            Token t = tokenMapper.selectByToken(token);
            // 如果token为空或者已经过期或者状态不正常
            if (t == null || t.getExpiredTime().before(new Date()) || t.getStat() != Token.STAT_OK) {
                return true;
            }
            // token有效，将当前用户暂时存放起来，之后在所有的地方都可以通过依赖注入的UserHolder获取当前用户
            User user = userMapper.selectById(t.getUid());
            userHolder.setUser(user);
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // response中添加header允许跨域请求
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("postHandle*************************************************************");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        userHolder.remove();
        // response中添加header允许跨域请求
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("after--------------------------------------------------------------------");
    }
}
