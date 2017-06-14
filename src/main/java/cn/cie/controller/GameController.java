package cn.cie.controller;

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

    @PostMapping(value = "{id}")
    @ResponseBody
    public Result selectById(@PathVariable(value = "id") Integer id) {
        return gameService.getById(id);
//        if (result.isSuccess()) {
//            this.getModel().addAttribute("game", result.getData());
//            return "gameInfo";
//        } else {
//            throw new NotFoundException();
//        }
    }

}
