package cn.cie.mapper;

import cn.cie.entity.Kind;

import java.util.List;

public interface KindMapper {

    int insert(Kind record);

    Kind selectById(Integer id);

    List<Kind> selectAll();

    List<Kind> selectByIds(List<Integer> ids);

    int delete(Integer id);

    int update(Kind record);

}