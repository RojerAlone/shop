package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Kind;
import cn.cie.entity.User;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.KindMapper;
import cn.cie.mapper.KindmapperMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.services.AdminService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/15.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private KindmapperMapper kindmapperMapper;
    @Autowired
    private RedisUtil redisUtil;


    public Result restrict(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_RESTRICT);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    public Result relieve(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_OK);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    @Transactional
    public Result addGame(Game game, Integer[] kind) {
        gameMapper.insert(game);
        kindmapperMapper.insertBatch(game.getId(), Arrays.asList(kind));
        return Result.success();
    }

    public Result upGame(Integer id, Date date) {
        Game game = gameMapper.selectById(id);
        // 如果没有这个游戏或者游戏的状态已经为已上架就返回参数错误
        if (game == null || game.getStat().equals(Game.STAT_OK)) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        game.setStat(Game.STAT_OK);
        if (date == null) {
            game.setUtime(new Date());
        } else {
            game.setUtime(date);
        }
        if (1 == gameMapper.update(game)) {
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    public Result downGame(Integer id) {
        Game game = gameMapper.selectById(id);
        // 如果没有这个游戏或者游戏的状态已经为已下架就返回参数错误
        if (game == null || game.getStat().equals(Game.STAT_OK)) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        game.setStat(Game.STAT_DEL);
        if (1 == gameMapper.update(game)) {
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    @Transactional
    public Result addKind(String name) {
        Kind kind = kindMapper.selectByName(name);
        if (kind != null) {
            return Result.fail(MsgCenter.NAME_EXISTS);
        }
        kind = new Kind();
        kind.setName(name);
        if (1 == kindMapper.insert(kind)) {
            redisUtil.rpushObject("kinds", Kind.class, kind);   // 添加到缓存中
            return Result.success();
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

}
