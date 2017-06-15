package cn.cie.controller;

import cn.cie.services.AdminService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by RojerAlone on 2017/6/15.
 */
@CrossOrigin
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "restrict")
    @ResponseBody
    public Result restrict(Integer uid) {
        return adminService.restrict(uid);
    }

    @PostMapping(value = "relieve")
    @ResponseBody
    public Result relieve(Integer uid) {
        return adminService.relieve(uid);
    }


}
