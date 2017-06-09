package cn.cie.mapper;

import cn.cie.entity.Validatecode;

import java.util.List;

public interface ValidatecodeMapper {

    int insert(Validatecode record);

    List<String> selectByUid(Integer uid);

    List<Validatecode> selectAll();

    int delete(Integer uid);

    int delByCode(Validatecode validatecode);

}