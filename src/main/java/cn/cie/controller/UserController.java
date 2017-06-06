package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by RojerAlone on 2017/6/6.
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractController{

    @Autowired
    private UserService userService;

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "login")
    public String login(String username, String password) {
        Result result = userService.login(username, password);
        if (result.isSuccess()) {       // 登录成功
            return "redirect:/index";
        } else {                        // 登录失败
            this.getModel().addAttribute("msg", result.getMsg());
            return "login";
        }
    }

    @GetMapping(value = "register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "register")
    public String register(User user) {
        Result result = userService.register(user);
        if (result.isSuccess()) {       // 注册成功跳转到登录页面
            return "login";
        } else {                        // 失败返回到注册页面
            this.getModel().addAttribute("msg", result.getMsg());
            return "register";
        }
    }

    @GetMapping(value = "update")
    public String udpate() {
        return "updateUserInfo";
    }

    @PostMapping(value = "update")
    public String update(User user) {
        if(userService.updateUserInfo(user)) {
            this.getModel().addAttribute("user", user);
            return "userinfo";
        } else {
            return "updateUserInfo";
        }
    }

    @PostMapping(value = "restrict")
    @ResponseBody
    public Result restrict(Integer uid) {
        return userService.restrict(uid);
    }

}
