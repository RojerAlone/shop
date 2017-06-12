package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RojerAlone on 2017/6/9.
 */
@CrossOrigin
@Controller
public class CommonController extends AbstractController {

    @Autowired
    private UserHolder userHolder;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "login")
    public String login() {
        String check = checkLogin();
        // 如果用户已经登陆，那么跳转到之前的页面
        if (check != null) {
            return "redirect:" + check;
        }
        return "login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) {
        Result result = userService.login(username, password);
        if (result.isSuccess()) {       // 登录成功，token加入到cookie中
            this.getSession().removeAttribute("Referer");   // 登陆成功就将session中的Referer删除
        }
        return result;
    }

    @PostMapping(value = "logout")
    @ResponseBody
    public Result logout() {
        String token = null;
        // 从请求中获取token
        if (this.getRequest().getCookies() != null) {
            for (Cookie cookie : this.getRequest().getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        return userService.logout(token);
    }

    @GetMapping(value = "register")
    public String register() {
        String referer = checkLogin();
        if (referer != null) {
            return "redirect:" + referer;
        }
        return "register";
    }

    @PostMapping(value = "register")
    @ResponseBody
    public Result register(User user, HttpServletResponse response) {
        Result result = userService.register(user);
        return result;
    }

    /**
     * 检查用户是否登陆，如果登陆就返回应该跳转到的页面，否则执行接下来的逻辑
     * 每次登陆之前都从session中获取跳转之前的链接referer
     * 如果为空，登陆成功那么跳转到首页，并且从session中删除referer
     * 登陆失败跳转到登录页，同时将"/"加入到session中，表示登陆成功后跳转到首页
     * 如果sesson中有referer，那么登陆成功跳转到referer，并且从session中删除referer
     *
     * @return
     */
    private String checkLogin() {
        // 用户没有登陆，返回null，表示未登录
        if (userHolder.getUser() == null) {
            return null;
        }
        // 用户已经登陆，获取应该跳转到的页面
        String referer = (String) this.getSession().getAttribute("Referer");
        // 登陆流程中第一次登陆
        if (referer == null) {
            String tmp = this.getRequest().getHeader("Referer");
            if (tmp == null || tmp.startsWith("http://127.0.0.1:8080/login") || tmp.startsWith("http://localhost:8080/login")) {
                referer = "/";
            } else {
                referer = tmp;
            }
        }
        return referer;
    }

}
