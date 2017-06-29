package cn.cie.aop;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by RojerAlone on 2017/6/8.
 * 日志记录，包括请求日志和错误日志
 */
@Component
@Aspect
public class LogAspectj {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Before("execution(* cn.cie.controller.*Controller.*(..)) && !execution( * cn.cie.controller.AbstractController.*(..))")   // 切面为controller中的所有方法
    public void logAccess(JoinPoint joinPoint) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("/").getPath() + "log4j-acc.properties");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuilder sb = new StringBuilder();
        String token = "";
        if (request.getCookies() != null && request.getCookies().length > 0) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        sb.append("token: " + token);
        sb.append("\tip: " + request.getRemoteAddr());
        sb.append("\tmethod: " + request.getMethod());
        sb.append("\turl: " + request.getRequestURI());
        sb.append("\tparams: ");
        for (Object object : joinPoint.getArgs()) {
             sb.append("\t" + object.getClass().getSimpleName() + " " + object.toString());
        }
        logger.info(sb.toString());
    }

    @AfterThrowing(value = "execution(* cn.cie.controller.*Controller.*(..)) && !execution( * cn.cie.controller.AbstractController.*(..))", throwing = "e")   // 切面为controller中的所有方法
    public void errorAccess(Throwable e) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("/").getPath() + "log4j-error.properties");
        if (e instanceof Exception) {
            logger.error(e);
        }
    }

}
