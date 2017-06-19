package cn.cie.services;

import cn.cie.entity.Game;
import cn.cie.utils.Result;

import java.util.Date;

/**
 * Created by RojerAlone on 2017/6/15.
 * 管理员相关
 */
public interface AdminService {

    /**
     * 限制账户操作
     * @param uid
     * @return
     */
    Result restrict(Integer uid);

    /**
     * 解除账户限制
     * @param uid
     * @return
     */
    Result relieve(Integer uid);

    /**
     * 添加游戏
     * @param game
     * @param kind
     * @return
     */
    Result addGame(Game game, Integer[] kind);

    /**
     * 将游戏上架
     * @param id
     * @param date 上架时间
     * @return
     */
    Result upGame(Integer id, Date date);

    /**
     * 下架游戏
     * @param id
     * @return
     */
    Result downGame(Integer id);

    /**
     * 添加游戏种类
     * @param name
     * @return
     */
    Result addKind(String name);

}
