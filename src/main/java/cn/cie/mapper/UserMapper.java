package cn.cie.mapper;

import cn.cie.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User record);

    User selectById(Integer id);

    User selectByName(String username);

    User selectByEmail(String email);

    int selectAllNums();

    List<User> selectByPage(@Param(value = "startPos") Integer startPos, @Param(value = "size") Integer size);

    List<User> selectByStat(Byte stat);

    int update(User record);

    int deleteById(Integer id);

}