package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Kind;
import cn.cie.entity.Tag;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.*;
import cn.cie.services.KindService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/6.
 */
@Service
public class KindServiceImpl implements KindService {

    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private KindmapperMapper kindmapperMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagmapperMapper tagmapperMapper;
    @Autowired
    private RedisUtil<Kind> redisUtil;

    public String getNameById(Integer id) {
        Kind kind = kindMapper.selectById(id);
        if (kind == null) {
            return null;
        }
        return kind.getName();
    }

    public Result<Kind> getAll() {
        redisUtil.setSchema(Kind.class);
        List<Kind> kinds = redisUtil.lall("kinds", Kind.class);
        // 如果缓存中没有，从数据库中查询，并且添加到缓存中
        if (kinds == null || kinds.size() == 0) {
            List<Kind> data = kindMapper.selectAll();
            Kind[] array = new Kind[data.size()];
            int index = 0;
            for (Kind k : data) {
                array[index] = k;
                index++;
            }
            redisUtil.rpushObject("kinds", Kind.class, array);
            return Result.success(data);
        }
        return Result.success(kinds);
    }

    public Result<List<GameDTO>> getGamesByKind(int kind) {
        if (kindMapper.selectById(kind) == null) {
//            throw new NotFoundException();
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        List<Integer> gameIds = kindmapperMapper.selectByKind(kind);
        List<Game> games = gameMapper.selectByIds(gameIds);
        List<GameDTO> gameDTOS = paresGameDTO(games);
        return Result.success(gameDTOS);
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<Tag> tags = null;
            List<Integer> tagIds = tagmapperMapper.selectByGame(game.getId());     // 获取游戏的标签id
            if (tagIds.size() != 0) {
                tags = tagMapper.selectByIds(tagIds);                         // 根据id获取所有的标签信息
            }
            List<String> img = imgMapper.selectByGame(game.getId());                // 获取所有的图片
            GameDTO dto = new GameDTO(game, tags, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }

}
