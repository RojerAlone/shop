package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RojerAlone on 2017/6/9.
 */
@Controller
public class CommonController extends AbstractController{

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
        return "login";
    }

    /**
     * 每次登陆之前都从session中获取跳转之前的链接referer
     * 如果为空，登陆成功那么跳转到首页，并且从session中删除referer
     * 登陆失败跳转到登录页，同时将"/"加入到session中，表示登陆成功后跳转到首页
     * 如果sesson中有referer，那么登陆成功跳转到referer，并且从session中删除referer
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "login")
    public String login(String username, String password, HttpServletResponse response) {
        String referer = (String) this.getSession().getAttribute("Referer");
        // 登陆流程中第一次登陆
        if (referer == null) {
            String tmp = this.getRequest().getHeader("Referer");
            if (tmp.startsWith("http://127.0.0.1:8080") || tmp.startsWith("http://localhost:8080/")) {
                referer = tmp;
            } else {
                referer = "/";
            }
        }
        // 用户已经登陆，就跳转
        if (userHolder.getUser() != null) {
            return "redirect:" + referer;
        }
        Result result = userService.login(username, password);
        if (result.isSuccess()) {       // 登录成功，token加入到cookie中
            this.getSession().removeAttribute("Referer");   // 登陆成功就将session中的Referer删除
            Cookie cookie = new Cookie("token", (String) result.getData());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:" + referer;
        } else {                        // 登录失败
            this.getModel().addAttribute("msg", result.getMsg());
            this.getSession().setAttribute("Referer", referer);     // 添加Referer到session中
            return "login";
        }
//        return result;
    }

    @GetMapping(value = "register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "register")
    @ResponseBody
    public Result register(User user, HttpServletResponse response) {
        Result result = userService.register(user);
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        if (result.isSuccess()) {       // 注册成功跳转到登录页面
//            return "login";
//        } else {                        // 失败返回到注册页面
//            this.getModel().addAttribute("msg", result.getMsg());
//            return "register";
//        }
        return result;
    }

}
