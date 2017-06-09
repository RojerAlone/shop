package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Tag;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.TagMapper;
import cn.cie.mapper.TagmapperMapper;
import cn.cie.services.GameService;
import cn.cie.common.utils.MsgCenter;
import cn.cie.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@Service
@RequestMapping(value = "game")
public class GameServiceImpl implements GameService{

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TagmapperMapper tagmapperMapper;
    @Autowired
    private TagMapper tagMapper;

    public Result<GameDTO> getById(Integer id) {
        Game game = gameMapper.selectById(id);
        if (game == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        List<Integer> tagIds = tagmapperMapper.selectByGame(id);
        List<Tag> tags = tagMapper.selectByIds(tagIds);
        GameDTO res = new GameDTO(game, tags);
        return Result.success(res);
    }
}
