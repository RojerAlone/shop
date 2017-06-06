package cn.cie.mapper;

import cn.cie.entity.Tagmapper;

import java.util.List;

public interface TagmapperMapper {
    int insert(Tagmapper record);

    List<Integer> selectByTag(Integer tag);

    List<Integer> selectByGame(Integer game);
}