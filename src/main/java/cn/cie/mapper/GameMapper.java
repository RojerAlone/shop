package cn.cie.mapper;

import cn.cie.entity.Game;

import java.util.List;

public interface GameMapper {

    int insert(Game record);

    Game selectById(Integer id);

    List<Game> selectByIds(List<Integer> ids);

    List<Game> selectByStat(Byte stat);

    int update(Game record);

}