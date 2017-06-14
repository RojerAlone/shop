package cn.cie.services;

import cn.cie.entity.dto.GameDTO;
import cn.cie.utils.Result;

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

}
