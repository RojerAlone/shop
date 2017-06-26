package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.GameService;
import cn.cie.services.UserService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.RedisUtil;
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
@CrossOrigin
@Controller
public class CommonController extends AbstractController {

    @Autowired
    private UserHolder userHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private RedisUtil redisUtil;


    @GetMapping(value = "test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        this.getModel().addAttribute("str", "str");
        return "index";
    }

    @GetMapping(value = "login")
    public String login() {
        String referer = getReferer();
        // 如果用户已经登陆并且状态正常，那么跳转到之前的页面
        if (userHolder.getUser() != null && userHolder.getUser().getStat().equals(User.STAT_OK)) {
            return "redirect:" + referer;
        }
        return "login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Result login(String username, String password,
                        @RequestParam(value = "remember", defaultValue = "false", required = false) boolean remember,
                        HttpServletResponse response) {
        String referer = getReferer();
        // 如果用户已经登陆，那么跳转到之前的页面
        if (userHolder.getUser() != null && userHolder.getUser().getStat().equals(User.STAT_OK)) {
            return Result.fail(MsgCenter.OK, referer);
        }
        Result result = userService.login(username, password, remember, this.getRemoteIp(), this.getUserAgent());
        if (result.isSuccess()) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("referer", referer);
            // response中添加cookie，以后每次请求都会带上cookie
            Cookie cookie = new Cookie("token", (String) result.getData());
            cookie.setPath("/");
            if (remember) {
                cookie.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cookie.setMaxAge(60 * 60 * 24);
            }
            response.addCookie(cookie);
            return Result.success(data);
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
        if (userHolder.getUser() != null) {
            return "redirect:" + referer;
        }
        return "register";
    }

    @PostMapping(value = "register")
    @ResponseBody
    public Result register(User user, HttpServletResponse response) {
        Result result = userService.register(user);
        String pwd = user.getPassword();
        // 注册成功就自动登录，前台跳转到验证页面
        if (result.isSuccess()) {
            login(user.getUsername(), pwd, false, response);
            return Result.success();
        }
        return result;
    }

    /**
     * 获取每日推荐，随机选取5个游戏，每日生成一次
     * @return
     */
    @PostMapping(value = "everyday")
    @ResponseBody
    public Result everyday() {
        return gameService.getRandomGames();
    }


    @GetMapping(value = "shoppingcart")
    public String shoppingcart() {
        return "shoppingcart";
    }



    /**
     * 检查用户是否登陆，如果登陆就返回应该跳转到的页面，否则执行接下来的逻辑
     * 每次登陆之前都从request的header中获取跳转之前的链接referer
     * 如果为空（从别的网站跳转过来的），那么应该跳转到首页
     * 登陆流程中第一次登陆就会调用本方法获取跳转链接，登陆失败将referer写入session中
     * 如果是从登录页跳转过来的，可能是登陆出错了，但是跳转到登录页之前的referer在session存着，从session中获取
     * 如果sesson中有referer，那么登陆成功跳转到referer，并且从session中删除referer
     * @return
     */
    private String getReferer() {
        String referer = null;
        String tmp = this.getRequest().getHeader("Referer");
        // 如果为空，不是从本站跳转过来的，应该跳转到首页
        if (tmp == null) {
            referer = "/";
        } else if (tmp.endsWith("/login")) {
            referer = (String) this.getSession().getAttribute("Referer");
        } else {
            referer = tmp;
        }
        this.getSession().setAttribute("Referer", referer);
        return referer;
    }

}
