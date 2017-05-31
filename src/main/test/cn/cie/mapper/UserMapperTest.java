package cn.cie.mapper;

import cn.cie.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by RojerAlone on 2017/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class UserMapperTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123qwe");
        user.setNickname("管理员");
        user.setEmail("email");
        try {
            if (userMapper.insert(user) == 1) {
                logger.info("插入成功");
            } else {
                logger.info("插入失败");
            }
        } catch (Exception e) {
            logger.error("出现错误", e);
        }
    }

    @Test
    public void selectById() throws Exception {
        int id = 1;
        try {
            User user = userMapper.selectById(id);
            if (user != null) {
                logger.info("User={}", user);
            } else {
                logger.info("查询失败");
            }
        } catch (Exception e) {
            logger.error("错误", e);
        }
    }

    @Test
    public void selectByStat() throws Exception {
        int stat = 0;
        try {
            List<User> users = userMapper.selectByStat(stat);
            if (users != null) {
                logger.info("Users={}", users);
            } else {
                logger.info("查询失败");
            }
        } catch (Exception e) {
            logger.error("错误", e);
        }
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        int id = 1;
        int stat = 1;
        user.setUid(id);
        user.setStat(stat);
        try {
            if (userMapper.update(user) == 1) {
                logger.info("更新成功");
            } else {
                logger.info("更新失败");
            }
        } catch (Exception e) {
            logger.error("出错");
        }
    }

}