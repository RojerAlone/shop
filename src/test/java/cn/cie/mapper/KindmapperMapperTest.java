package cn.cie.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring-dao.xml", "classpath:spring-service.xml"})
public class KindmapperMapperTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KindmapperMapper kindmapperMapper;

    @Test
    public void insertBatch() throws Exception {
        int game = 1;
        List<Integer> kinds = new ArrayList<Integer>();
        kinds.add(1);
        kinds.add(2);
        kinds.add(3);
        kinds.add(4);
        logger.info(String.valueOf(kindmapperMapper.insertBatch(game, kinds)));

    }

}