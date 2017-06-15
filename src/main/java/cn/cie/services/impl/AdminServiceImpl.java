package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.User;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.KindMapper;
import cn.cie.mapper.KindmapperMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.services.AdminService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Result addGame(Game game, List<Integer> kinds) {
        return null;
    }

    public Result upGame(Integer id) {
        return null;
    }

    public Result downGame(Integer id) {
        return null;
    }

    public Result addKind(String name) {
        return null;
    }

}
