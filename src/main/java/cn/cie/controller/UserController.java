package cn.cie.controller;

import cn.cie.entity.User;
import cn.cie.services.UserService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by RojerAlone on 2017/6/6.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserHolder userHolder;

    @GetMapping(value = "validate")
    public String validate() {
        String referer = getReferer();
        if (userHolder.getUser().getStat() == User.STAT_OK) {
            return "redirect:" + referer;
        }
        return "validate";
    }

    @PostMapping(value = "validate")
    @ResponseBody
    public Result validate(String code) {
        if (userHolder.getUser() == null) {
            return Result.fail(MsgCenter.USER_NOT_LOGIN);
        }
        if (userHolder.getUser().getStat() == User.STAT_OK) {    // 用户已经验证过了
            return Result.fail(MsgCenter.USER_VALIDATED);
        }
        Result result = userService.validate(userHolder.getUser().getId(), code);
        if (result.isSuccess()) {
            return Result.success(getReferer());
        }
        return result;
    }

    @PostMapping(value = "sendMail")
    @ResponseBody
    public Result sendMail() {
        return userService.sendMail(userHolder.getUser().getId());
    }

    @GetMapping(value = "update")
    public String udpate() {
        return "updateUserInfo";
    }

    @PostMapping(value = "update")
    public String update(User user) {
        if (userService.updateUserInfo(user)) {
            this.getModel().addAttribute("user", user);
            return "userinfo";
        } else {
            return "updateUserInfo";
        }
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
