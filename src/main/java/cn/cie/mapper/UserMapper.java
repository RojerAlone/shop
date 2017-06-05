package cn.cie.mapper;

import cn.cie.entity.User;

public interface UserMapper {

    int insert(User record);

    User selectById(Integer id);

    User selectByName(String username);

    User selectByEmail(String email);

    int update(User record);

}