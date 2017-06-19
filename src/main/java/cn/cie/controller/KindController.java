package cn.cie.controller;

import cn.cie.exception.NotFoundException;
import cn.cie.services.KindService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by RojerAlone on 2017/6/6.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "kind")
public class KindController extends AbstractController {

    @Autowired
    private KindService kindService;

    @PostMapping(value = "all")
    @ResponseBody
    public Result allkinds(HttpServletResponse response) {
        Result result = kindService.getAll();
        return result;
    }

    @GetMapping(value = "{kind}/games")
    public String getGames(@PathVariable(value = "kind") Integer kind) {
        String name = kindService.getNameById(kind);
        if (name == null) {
            throw new NotFoundException();
        }
        this.getModel().addAttribute("id", kind);
        this.getModel().addAttribute("name", name);
        return "kinds";
    }

    @PostMapping(value = "{kind}/games")
    @ResponseBody
    public Result getGamesByKind(@PathVariable(value = "kind") Integer kind) {
        return kindService.getGamesByKind(kind);
    }

}
