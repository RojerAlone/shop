package cn.cie.mapper;

import cn.cie.entity.Kindmapper;

import java.util.List;

public interface KindmapperMapper {
    int insert(Kindmapper record);

    List<Integer> selectByKind(Integer kind);

    List<Integer> selectByGame(Integer game);
}