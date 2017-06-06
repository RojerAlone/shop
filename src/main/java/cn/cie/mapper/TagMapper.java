package cn.cie.mapper;

import cn.cie.entity.Tag;

public interface TagMapper {

    int insert(Tag record);

    Tag selectById(Integer id);

    int update(Tag record);

}