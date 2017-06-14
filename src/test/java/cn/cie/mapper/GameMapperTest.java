package cn.cie.mapper;

import cn.cie.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by RojerAlone on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring-dao.xml", "classpath:spring-service.xml"})
public class GameMapperTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GameMapper gameMapper;

    @Test
    public void selectByStat() throws Exception {
        logger.info("games={}" + gameMapper.selectByStat(Game.STAT_OK));

    }

}