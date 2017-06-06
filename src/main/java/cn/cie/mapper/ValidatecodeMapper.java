package cn.cie.mapper;

import cn.cie.entity.Validatecode;

public interface ValidatecodeMapper {

    int insert(Validatecode record);

    String selectByUid(Integer uid);

    int delete(Integer uid);

}