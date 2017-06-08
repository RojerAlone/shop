package cn.cie.services;

import cn.cie.entity.dto.GameDTO;
import cn.cie.common.utils.Result;

/**
 * Created by RojerAlone on 2017/6/8.
 */
public interface GameService {

    Result<GameDTO> getById(Integer id);

}
