package cn.cie.mapper;

import cn.cie.entity.Kindmapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KindmapperMapper {

    int insert(Kindmapper record);

    int insertBatch(@Param(value = "game") Integer game, @Param(value = "kinds") List<Integer> kinds);

    List<Integer> selectByKind(Integer kind);

    List<Integer> selectByGame(Integer game);

    int deleteByGame(Integer game);

}