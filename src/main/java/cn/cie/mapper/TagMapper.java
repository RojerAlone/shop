package cn.cie.mapper;

import cn.cie.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {

    int insert(Tag record);

    Tag selectById(Integer id);

    List<Tag> selectByIds(@Param(value = "ids") List<Integer> ids);

    List<Integer> selectIdByLikeName(String info);

    List<Tag> selectAll();

    int update(Tag record);

}