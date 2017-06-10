package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) {
        Result result = userService.login(username, password);
//        if (result.isSuccess()) {       // 登录成功
//            this.getSession().setAttribute("user", result.getData());
//            return "redirect:/index";
//        } else {                        // 登录失败
//            this.getModel().addAttribute("msg", result.getMsg());
//            return "login";
//        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result;
    }

    @GetMapping(value = "register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "register")
    @ResponseBody
    public Result register(User user, HttpServletResponse response) {
        Result result = userService.register(user);
        response.setHeader("Access-Control-Allow-Origin", "*");
//        if (result.isSuccess()) {       // 注册成功跳转到登录页面
//            return "login";
//        } else {                        // 失败返回到注册页面
//            this.getModel().addAttribute("msg", result.getMsg());
//            return "register";
//        }
        return result;
    }

    @GetMapping(value = "validate")
    public String validate() {
        return "validate";
    }

    @PostMapping(value = "validate")
    @ResponseBody
    public Result validate(Integer uid, String code, HttpServletResponse response) {
//        int uid = ((User) this.getSession().getAttribute("user")).getId();
//        Result result = userService.validate(uid, code);
//        if (result.isSuccess()) {       // 验证成功返回首页
//            return "redirect:index";
//        } else {                        // 验证失败返回验证页
//            this.getModel().addAttribute("msg", result.getMsg());
//            return "validate";
//        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userService.validate(uid, code);
    }

    @PostMapping(value = "sendMail")
    @ResponseBody
    public Result sendMail(@RequestParam Integer uid, HttpServletResponse response) {
//        int uid = ((User) this.getSession().getAttribute("user")).getId();
//        Result result = userService.validate(uid, code);
//        if (result.isSuccess()) {       // 验证成功返回首页
//            return "redirect:index";
//        } else {                        // 验证失败返回验证页
//            this.getModel().addAttribute("msg", result.getMsg());
//            return "validate";
//        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userService.sendMail(uid);
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

    @PostMapping(value = "relieve")
    @ResponseBody
    public Result relieve(Integer uid) {
        return userService.relieve(uid);
    }

}
