package cn.cie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by RojerAlone on 2017/6/9.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

}
