package cn.cie.controller;

import cn.cie.entity.Kind;
import cn.cie.services.KindService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/6.
 */
@Controller
@RequestMapping(value = "kind")
public class KindController extends AbstractController {

    @Autowired
    private KindService kindService;

    @GetMapping(value = "all")
    public String allkinds() {
        Result result = kindService.getAll();
        this.getModel().addAttribute("kinds", (List<Kind>) result.getData());
        return "allkinds";
    }

    @GetMapping(value = "{kind}")
    public String getGamesByKind(@PathVariable(value = "kind") Integer kind) {
        Result result = kindService.getGamesByKind(kind);
        this.getModel().addAttribute("games", result.getData());
        return "kindsOfGame";
    }

}
