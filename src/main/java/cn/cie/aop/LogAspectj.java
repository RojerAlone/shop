package cn.cie.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@Aspect
public class LogAspectj {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* cn.cie.controller.*Controller.*())")   // 切面为controller中的所有方法
    public void logParams(JoinPoint joinPoint) {

    }

}
