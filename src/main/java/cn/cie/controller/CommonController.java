package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RojerAlone on 2017/6/9.
 */
//@CrossOrigin
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
        String referer = getReferer();
        // 如果用户已经登陆，那么跳转到之前的页面
        if (userHolder.getUser() != null) {
            return "redirect:" + referer;
        }
        return "login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) {
        String referer = getReferer();
        // 如果用户已经登陆，那么跳转到之前的页面
        if (userHolder.getUser() != null) {
            return Result.fail(MsgCenter.OK, referer);
        }
        Result result = userService.login(username, password, this.getRemoteIp(), this.getUserAgent());
        if (result.isSuccess()) {
            String token = (String) result.getData();
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("referer", referer);
            // response中添加cookie，以后每次请求都会带上cookie
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            return Result.success(map);
        } else {
            return result;
        }
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
        String referer = getReferer();
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
     * 每次登陆之前都从request的header中获取跳转之前的链接referer
     * 如果为空或者从别的网站跳转过来的，那么应该跳转到首页
     * 登陆流程中第一次登陆就会调用本方法获取跳转链接，登陆失败将referer写入session中
     * 如果是从登录页跳转过来的，可能是登陆出错了，但是跳转到登录页之前的referer在session存着，从session中获取
     * 如果sesson中有referer，那么登陆成功跳转到referer，并且从session中删除referer
     * @return
     */
    private String getReferer() {
        String referer = null;
        String tmp = this.getRequest().getHeader("Referer");
        // 如果为空或者不是从本站跳转过来的，应该跳转到首页
        if (tmp == null || !tmp.startsWith("http://localhost:8080") || !tmp.startsWith("http://127.0.0.1:8080")) {
            referer = "/";
        } else if (tmp.startsWith("http://127.0.0.1:8080/login") || tmp.startsWith("http://localhost:8080/login")) {
            referer = (String) this.getSession().getAttribute("Referer");
        } else {
            referer = tmp;
            this.getSession().setAttribute("Referer", referer);
        }
        return referer;
    }

}
