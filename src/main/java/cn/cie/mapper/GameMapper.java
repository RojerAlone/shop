package cn.cie.mapper;

import cn.cie.entity.Game;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameMapper {

    int insert(Game record);

    Game selectById(Integer id);

    List<Game> selectByIds(@Param(value = "ids") List<Integer> ids);

    List<Game> selectByStat(Byte stat);

    int update(Game record);

}