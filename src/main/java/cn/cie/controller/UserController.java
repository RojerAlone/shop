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
        return "validate";
    }

    @PostMapping(value = "validate")
    @ResponseBody
    public Result validate(String code) {
        if (userHolder.getUser().getStat() == User.STAT_OK) {    // 用户已经验证过了
            return Result.fail(MsgCenter.USER_VALIDATED);
        }
        return userService.validate(userHolder.getUser().getId(), code);
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
