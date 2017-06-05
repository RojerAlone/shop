package cn.cie.mapper;

import cn.cie.entity.Code;

public interface CodeMapper {

    int insert(Code record);

    Code selectById(Integer id);

    int update(Code record);

}