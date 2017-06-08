package cn.cie.controller;

import cn.cie.common.exception.NotFoundException;
import cn.cie.entity.dto.GameDTO;
import cn.cie.services.TagService;
import cn.cie.common.utils.MsgCenter;
import cn.cie.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/7.
 */
@Controller
@RequestMapping(value = "tag")
public class TagController extends AbstractController{

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/{tag}/games")
    public String getGamesByTag(@PathVariable(value = "tag") Integer tag) {
        Result result = tagService.getGamesByTag(tag);
        if (result.isSuccess()) {
            this.getModel().addAttribute("games", (List<GameDTO>) result.getData());
        } else if (result.getMsg().equals(MsgCenter.NOT_FOUND)) {
            throw new NotFoundException();
        } else {
            this.getModel().addAttribute("msg", result.getMsg());
        }
        return "taggames";
    }

}
