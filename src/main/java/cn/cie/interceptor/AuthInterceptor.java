package cn.cie.interceptor;

import cn.cie.entity.Token;
import cn.cie.entity.User;
import cn.cie.mapper.TokenMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by RojerAlone on 2017/6/11.
 * 拦截器，根据token获取用户身份
 * 先从缓存中查找，如果找不到再从数据库中找
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 如果请求静态资源直接pass
        if (httpServletRequest.getRequestURI().matches("^/img/[\\S]+\\.(png|jpg)$") || httpServletRequest.getRequestURI().matches("^/css/[\\S]+\\.css$")
                || httpServletRequest.getRequestURI().matches("^/js/[\\S]+\\.js$")) {
            return true;
        }
//        System.out.println("auth拦截器--------------------------prehandle----------------------------");
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
            String userid = redisUtil.get(token);
            int uid = 0;
            // 缓存中没有token，从数据库中查找
            if (userid == null) {
                Token t = tokenMapper.selectByTokenAndStat(token, Token.STAT_OK);
                // 如果token为空或者已经过期但是定时任务还没有来得及更改状态
                Date now = new Date();
                if (t == null || t.getExpiredTime().before(now)) {
                    return true;
                }
                // token不为空，但是缓存中没有了，将token加入缓存中
                uid = t.getUid();
                long oneday = 86400000L;
                long expired = t.getExpiredTime().getTime() - now.getTime();
                // 如果token剩余有效期大于1天，加入缓存中的token有效期为1天
                if (expired > oneday) {
                    redisUtil.putEx(token, String.valueOf(uid), 60 * 60 * 24);
                } else {    // 否则有效期为剩下的时间
                    redisUtil.putEx(token, String.valueOf(uid), (int) (expired / 1000));
                }
            } else {
                uid = Integer.valueOf(userid);
            }
            // token有效，将当前用户暂时存放起来，之后在所有的地方都可以通过依赖注入的UserHolder获取当前用户
            User user = userMapper.selectById(uid);
            if (user == null) {
                return true;
            }
            userHolder.setUser(user);
            // 跳过post方法
            if (httpServletRequest.getMethod().equals("POST")) {
                return true;
            }
            // 如果用户登陆成功但是没有验证邮箱，跳转到邮箱验证页面(防止请求的url就是验证页时会循环跳转)
            if (user.getStat().equals(User.STAT_NOT_VALIDATE) && !httpServletRequest.getRequestURI().equals("/user/validate")) {
                httpServletResponse.sendRedirect("/user/validate");
                return false;
            } else if (user.getStat().equals(User.STAT_DEL)) {      // 用户被删除
                userHolder.remove();
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("auth拦截器--------------------------post----------------------------");
        if (httpServletRequest.getMethod().equals("GET") && userHolder.getUser() != null) {
            modelAndView.getModel().put("user", userHolder.getUser().getNickname());
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("auth拦截器--------------------------after----------------------------");
        userHolder.remove();
    }
}
