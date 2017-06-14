package cn.cie.controller;

import cn.cie.entity.dto.GameDTO;
import cn.cie.services.TagService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/7.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "tag")
public class TagController extends AbstractController{

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/{tag}/games")
    @ResponseBody
    public Result<List<GameDTO>> getGamesByTag(@PathVariable(value = "tag") Integer tag) {
        return tagService.getGamesByTag(tag);
//        if (result.isSuccess()) {
//            this.getModel().addAttribute("games", (List<GameDTO>) result.getData());
//        } else if (result.getMsg().equals(MsgCenter.NOT_FOUND)) {
//            throw new NotFoundException();
//        } else {
//            this.getModel().addAttribute("msg", result.getMsg());
//        }
    }

}
