package cn.cie.mapper;

import cn.cie.entity.Tagmapper;

public interface TagmapperMapper {
    int insert(Tagmapper record);

    int insertSelective(Tagmapper record);
}