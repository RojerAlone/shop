/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.cie.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 从左潇龙博客源码复制过来
 * 基础controller,各个web项目应该在此基础上扩展自己的基础controller.
 *
 * @author Boren You
 * @date 2016/5/12 21:28
 * @since 1.0.0
 */
public abstract class AbstractController {

    /**
     * 日志对象
     */
    protected Logger logger = Logger.getLogger(this.getClass());

    /**
     * 存放当前线程的HttpServletRequest对象
     */
    private static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<HttpServletRequest>();

    /**
     * 存放当前线程的Model对象
     */
    private static ThreadLocal<Model> modelThreadLocal = new ThreadLocal<Model>();

    /**
     * 使用@ModelAttribute注解标识的方法会在每个控制器中的方法访问之前先调用
     *
     * @param request
     * @param model
     */
    @ModelAttribute
    protected void setThreadLocal(HttpServletRequest request, Model model) {
        httpServletRequestThreadLocal.set(request);
        modelThreadLocal.set(model);
    }

    /**
     * 获取当前线程的HttpServletRequest对象
     *
     * @return 当前线程的HttpServletRequest对象
     */
    protected HttpServletRequest getRequest() {
        return httpServletRequestThreadLocal.get();
    }

    /**
     * 获取当前线程的HttpSession对象
     *
     * @return 当前线程的HttpSession对象
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前线程的Model对象
     *
     * @return 当前线程的Model对象
     */
    protected Model getModel() {
        return modelThreadLocal.get();
    }

    /**
     * 获取当前的ServletContext对象
     *
     * @return 当前的ServletContext对象
     */
    protected ServletContext getContext() {
        return getRequest().getServletContext();
    }

    /**
     * 向 Model 设置属性值
     *
     * @param name  属性名
     * @param value 属性值
     */
    protected void setModelAttribute(String name, Object value) {
        getModel().addAttribute(name, value);
    }

    /**
     * 向 HttpServletRequest 设置属性值
     *
     * @param name  属性名
     * @param value 属性值
     */
    protected void setRequestAttribute(String name, Object value) {
        HttpServletRequest request = this.getRequest();
        request.setAttribute(name, value);
    }

    /**
     * 向 HttpSession 设置属性值
     *
     * @param name  属性名
     * @param value 属性值
     */
    public void setSessionAttribute(String name, Object value) {
        HttpSession session = this.getSession();
        session.setAttribute(name, value);
    }

    /**
     * 从 HttpSession 中获取属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    protected Object getSessionAttribute(String name) {
        HttpSession session = this.getSession();
        Object value = session.getAttribute(name);
        return value;
    }

    /**
     * 从 HttpServletRequest 中获取属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    protected Object getRequestAttribute(String name) {
        HttpServletRequest request = this.getRequest();
        Object value = request.getAttribute(name);
        return value;
    }

    protected String getRemoteIp() {
        String remoteIp;
        remoteIp = this.getRequest().getHeader("x-forwarded-for");
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("HTTP_CLIENT_IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("HTTP_X_FORWARDED-FOR");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getRemoteAddr();
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (remoteIp != null && remoteIp.length() > 15) { //"***.***.***.***".length() = 15
            if (remoteIp.indexOf(",") > 0) {
                remoteIp = remoteIp.substring(0, remoteIp.indexOf(","));
            }
        }
        return remoteIp;
    }

}
