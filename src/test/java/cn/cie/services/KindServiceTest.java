package cn.cie.services;

import cn.cie.utils.Result;
import cn.cie.entity.dto.GameDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring-dao.xml", "classpath:spring-service.xml"})
public class KindServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KindService kindService;

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void getGamesByKind() throws Exception {
        int kind = 1;
        try {
            Result<List<GameDTO>> result = kindService.getGamesByKind(kind);
            if (result.isSuccess()) {
                logger.info("查询结果为：{}" + result.getData());
            } else {
                logger.info("查询失败");
            }
        } catch (Exception e) {
            logger.error("出错：", e);
        }
    }

}