package cn.cie.services;

import cn.cie.entity.dto.GameDTO;
import cn.cie.utils.Result;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/8.
 */
public interface GameService {

    /**
     * 根据游戏id获取游戏详细信息
     * @param id
     * @return
     */
    Result<GameDTO> getById(Integer id);

    /**
     * 获取随机图片，用于每日推荐
     * @return
     */
    Result<List<GameDTO>> getRandomGames();

}
