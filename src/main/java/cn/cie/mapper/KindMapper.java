package cn.cie.mapper;

import cn.cie.entity.Kind;

public interface KindMapper {

    int insert(Kind record);

    Kind selectById(Integer id);

    int delete(Integer id);

    int update(Kind record);

}