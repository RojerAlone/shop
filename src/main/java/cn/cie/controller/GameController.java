package cn.cie.controller;

import cn.cie.exception.NotFoundException;
import cn.cie.services.GameService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "game")
public class GameController extends AbstractController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "{id}")
    public String gameInfo(@PathVariable(value = "id") Integer id) {
        if (!gameService.exists(id)) {
            throw new NotFoundException();
        }
        this.getModel().addAttribute("id", id);
        return "gameInfo";
    }

    @PostMapping(value = "{id}")
    @ResponseBody
    public Result selectById(@PathVariable(value = "id") Integer id) {
        return gameService.getById(id);
    }

}
