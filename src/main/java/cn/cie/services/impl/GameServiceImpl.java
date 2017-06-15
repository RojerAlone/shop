package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Tag;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.ImgMapper;
import cn.cie.mapper.TagMapper;
import cn.cie.mapper.TagmapperMapper;
import cn.cie.services.GameService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

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
    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private RedisUtil<GameDTO> redisUtil;

    public Result<GameDTO> getById(Integer id) {
        Game game = gameMapper.selectById(id);
        if (game == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        List<Integer> tagIds = tagmapperMapper.selectByGame(id);
        List<Tag> tags = tagMapper.selectByIds(tagIds);
        List<String> img = imgMapper.selectByGame(game.getId());
        GameDTO res = new GameDTO(game, tags, img);
        return Result.success(res);
    }

    public Result<List<GameDTO>> getRandomGames() {
        List<Game> allgames = gameMapper.selectByStat(Game.STAT_OK);
        List<GameDTO> res = null;
        int count = allgames.size();
        Set<Integer> numSet = new HashSet<Integer>();
        Random random = new Random();
        List<Game> games = new ArrayList<Game>();
        // 如果游戏数量大于5个就随机取5个，否则取全部的
        if (count > 5) {
            while (numSet.size() < 5) {
                numSet.add(random.nextInt(count));
            }
            Iterator i = numSet.iterator();
            while (i.hasNext()) {
                games.add(allgames.get((Integer) i.next()));
            }
        } else {
            games = allgames;
        }
        res = paresGameDTO(games);
        GameDTO[] data = new GameDTO[res.size()];
        int index = 0;
        for (GameDTO g : res) {
            data[index] = g;
            index++;
        }
        // 将数据存入缓存中
        redisUtil.setSchema(GameDTO.class);
        int tmp = 1000 * 3600 * 24;
        long zero = (System.currentTimeMillis() / tmp * tmp + tmp - TimeZone.getDefault().getRawOffset()) / 1000;    //明天零点零分零秒的unix时间戳
        redisUtil.rpushObjectExAtTime("everyday",GameDTO.class, zero, data);
        return Result.success(res);
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<String> img = imgMapper.selectByGame(game.getId());                // 获取所有的图片
            GameDTO dto = new GameDTO(game, null, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }
}
