package cn.cie.controller;

import cn.cie.services.KindService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by RojerAlone on 2017/6/6.
 */
@Controller
@RequestMapping(value = "kind")
public class KindController extends AbstractController {

    @Autowired
    private KindService kindService;

    @GetMapping(value = "all")
    @ResponseBody
    public Result allkinds(HttpServletResponse response) {
        Result result = kindService.getAll();
//        this.getModel().addAttribute("kinds", (List<Kind>) result.getData());
//        return "allkinds";
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result;
    }

    @GetMapping(value = "{kind}/games")
    @ResponseBody
    public Result getGamesByKind(@PathVariable(value = "kind") Integer kind, HttpServletResponse response) {
//        Result result = kindService.getGamesByKind(kind);
//        if (result.isSuccess()) {
//            this.getModel().addAttribute("games", result.getData());
//            return "kindsOfGame";
//        } else {
//            throw new NotFoundException();
//        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        return kindService.getGamesByKind(kind);
    }

}
