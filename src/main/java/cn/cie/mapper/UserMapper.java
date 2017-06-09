package cn.cie.mapper;

import cn.cie.entity.User;

import java.util.List;

public interface UserMapper {

    int insert(User record);

    User selectById(Integer id);

    User selectByName(String username);

    User selectByEmail(String email);

    List<User> selectByStat(Byte stat);

    int update(User record);

    int deleteById(Integer id);

}