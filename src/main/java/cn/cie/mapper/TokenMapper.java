package cn.cie.mapper;

import cn.cie.entity.Token;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TokenMapper {

    int insert(Token record);

    Token selectByToken(String token);

    List<Token> selectByUid(Integer uid);

    int updateStatByDate(@Param(value = "date") Date date, @Param(value = "stat") Byte stat);
}