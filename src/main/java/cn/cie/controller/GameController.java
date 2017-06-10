package cn.cie.controller;

import cn.cie.common.exception.NotFoundException;
import cn.cie.services.GameService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@Controller
@RequestMapping(value = "game")
public class GameController extends AbstractController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "{id}")
    public String selectById(@PathVariable(value = "id") Integer id) {
        Result result = gameService.getById(id);
        if (result.isSuccess()) {
            this.getModel().addAttribute("game", result.getData());
            return "gameInfo";
        } else {
            throw new NotFoundException();
        }
    }

}
