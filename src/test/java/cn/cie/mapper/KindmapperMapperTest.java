package cn.cie.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private KindMapper kindMapper;

    @Test
    public void insertBatch() throws Exception {
        int game = 1;
        List<Integer> kinds = new ArrayList<Integer>();
        kinds.add(1);
        kinds.add(2);
        kinds.add(3);
        kinds.add(4);
        Integer[] test = new Integer[0];
        try {
            kindmapperMapper.insertKindBatch(game, Arrays.asList(test));
        } catch (Exception e) {
            logger.error("错误" + e);
        }
    }

    @Test
    public void selectIdByLikeName() throws Exception {
        String info = "人";
        List<Integer> res = kindMapper.selectIdByLikeName(info);
        logger.info(String.valueOf(res));
    }

}