package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Kind;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.KindMapper;
import cn.cie.mapper.KindmapperMapper;
import cn.cie.services.KindService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Result<Kind> getAll() {
        return Result.success(kindMapper.selectAll());
    }

    public Result<List<GameDTO>> getGamesByKind(int kind) {
        List<Integer> gameIds = kindmapperMapper.selectByKind(kind);
        List<Game> games = gameMapper.selectByIds(gameIds);
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<Integer> kindIds = kindmapperMapper.selectByGame(game.getId());    // 获取游戏属于的种类id
            List<Kind> kinds = kindMapper.selectByIds(kindIds);                      // 根据id获取所有的种类信息
            GameDTO dto = new GameDTO(game, kinds);
            gameDTOS.add(dto);
        }
        return Result.success(gameDTOS);
    }
}
