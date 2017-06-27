package cn.cie.mapper;

import cn.cie.entity.Kind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KindMapper {

    int insert(Kind record);

    Kind selectById(Integer id);

    List<Kind> selectAll();

    List<Kind> selectByIds(@Param(value = "ids") List<Integer> ids);

    Kind selectByName(String name);

    List<Integer> selectIdByLikeName(String name);

    int delete(Integer id);

    int update(Kind record);

}