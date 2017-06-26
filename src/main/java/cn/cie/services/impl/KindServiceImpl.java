package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Kind;
import cn.cie.entity.Tag;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.*;
import cn.cie.services.KindService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.PageUtil;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

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
        List<Kind> kinds = redisUtil.lall(RedisUtil.KINDS, Kind.class);
        // 如果缓存中没有，从数据库中查询，并且添加到缓存中
        if (kinds == null || kinds.size() == 0) {
            List<Kind> data = kindMapper.selectAll();
            Kind[] array = new Kind[data.size()];
            int index = 0;
            for (Kind k : data) {
                array[index] = k;
                index++;
            }
            redisUtil.rpushObject(RedisUtil.KINDS, Kind.class, array);
            return Result.success(data);
        }
        return Result.success(kinds);
    }

    public Result<List<GameDTO>> getGamesByKind(int kind, int page) {
        if (kindMapper.selectById(kind) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        List<Integer> gameIds = kindmapperMapper.selectByKind(kind);
        // 如果没有就返回
        if (gameIds == null || gameIds.size() == 0) {
            return Result.success();
        }
        List<Game> games = gameMapper.selectByIdsAndStat(gameIds, Game.STAT_OK);
        PageUtil pageUtil = new PageUtil(games.size(), page);
        // 假分页
        int size = pageUtil.getStartPos() + 10 > games.size() - 1 ? games.size() : pageUtil.getStartPos() + 10;
        List<GameDTO> gameDTOS = paresGameDTO(games.subList(pageUtil.getStartPos(), size));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("game", gameDTOS);
        map.put("page", pageUtil);
        return Result.success(map);
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<Tag> tags = null;
            List<Integer> tagIds = tagmapperMapper.selectByGame(game.getId());     // 获取游戏的标签id
            if (tagIds.size() != 0) {
                tags = tagMapper.selectByIds(tagIds);                               // 根据id获取所有的标签信息
            }
            List<String> img = imgMapper.selectByGame(game.getId());                // 获取所有的图片
            GameDTO dto = new GameDTO(game, tags, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }

}
