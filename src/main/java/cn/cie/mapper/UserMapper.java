package cn.cie.mapper;

import cn.cie.entity.User;

import java.util.List;

/**
 * Created by RojerAlone on 2017/5/31.
 */
public interface UserMapper {

    public int insert(User user);
    public User selectById(int uid);
    public List<User> selectByStat(int stat);
    public int update(User user);

}
