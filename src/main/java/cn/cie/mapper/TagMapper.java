package cn.cie.mapper;

import cn.cie.entity.Tag;

import java.util.List;

public interface TagMapper {

    int insert(Tag record);

    Tag selectById(Integer id);

    List<Tag> selectByIds(List<Integer> ids);

    List<Tag> selectAll();

    int update(Tag record);

}