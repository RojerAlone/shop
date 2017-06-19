package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Tag;
import cn.cie.entity.Tagmapper;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.ImgMapper;
import cn.cie.mapper.TagMapper;
import cn.cie.mapper.TagmapperMapper;
import cn.cie.services.TagService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/7.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagmapperMapper tagmapperMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private ImgMapper imgMapper;

    public String getNameById(Integer id) {
        Tag tag = tagMapper.selectById(id);
        if (tag == null) {
            return null;
        }
        return tag.getName();
    }

    public Result<List<Tag>> getAll() {
        return Result.success(tagMapper.selectAll());
    }

    public Result<Tag> addTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        if (1 == tagMapper.insert(tag)) {
            return Result.success(tag);
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

    @Transactional
    public Result addTag(String name, Integer game) {
        // 如果没有这个游戏，就返回404
        if (gameMapper.selectById(game) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        Result result = addTag(name);
        if (result.isSuccess()) {
            Tagmapper tagmapper = new Tagmapper();
            tagmapper.setGame(game);
            tagmapper.setTag(((Tag) result.getData()).getId());
            if (1 == tagmapperMapper.insert(tagmapper)) {
                return Result.success();
            }
        }
        return Result.fail(MsgCenter.ERROR);
    }

    public Result addTag(Integer tag, Integer game) {
        // 如果没有这个游戏，就返回404
        if (gameMapper.selectById(game) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        Tagmapper tagmapper = new Tagmapper();
        tagmapper.setGame(game);
        tagmapper.setTag(tag);
        if (1 == tagmapperMapper.insert(tagmapper)) {
            return Result.success();
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

    public Result<List<GameDTO>> getGamesByTag(Integer tag) {
        // 没有这个标签，返回404
        if (tagMapper.selectById(tag) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        List<Integer> gameIds = tagmapperMapper.selectByTag(tag);                  // 根据标签获取所有的游戏ID
        List<Game> games = gameMapper.selectByIds(gameIds);                         // 根据游戏ID获取游戏实体
        List<GameDTO> gameDTOS = paresGameDTO(games);
        return Result.success(gameDTOS);
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<Integer> tagIds = tagmapperMapper.selectByGame(game.getId());     // 获取游戏的标签id
            List<Tag> tags = tagMapper.selectByIds(tagIds);                         // 根据id获取所有的标签信息
            List<String> img = imgMapper.selectByGame(game.getId());                // 获取所有的图片
            GameDTO dto = new GameDTO(game, tags, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }
}
