package cn.cie.common;

import cn.cie.event.EventModel;
import cn.cie.event.EventType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by RojerAlone on 2017/6/10.
 */
public class CommonTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void createSql() {
        int nums = 31;
        List<Integer> sixPics = new ArrayList<Integer>();
        sixPics.add(7);
        sixPics.add(10);
        sixPics.add(11);
        sixPics.add(12);
        sixPics.add(13);
        sixPics.add(16);
        sixPics.add(17);
        sixPics.add(18);
        sixPics.add(21);
        sixPics.add(30);
        sixPics.add(31);
        for (int i = 1; i <= nums; ++i) {
            System.out.print("(" + i + ", '/" + i + "/header.jpg'), ");
            if (sixPics.contains(i)) {
                for (int j = 1; j <= 6; ++j) {
                    System.out.print("(" + i + ", '/" + i + "/" + j + ".jpg'), ");
                }
            } else {
                for (int j = 1; j <= 5; ++j) {
                    System.out.print("(" + i + ", '/" + i + "/" + j + ".jpg'), ");
                }
            }
        }
    }

    @Test
    public void random() {
        int num = 31;
        Random random = new Random();
        System.out.println(random.nextInt(num));
    }

    @Test
    public void zeroTime() {
        int tmp = 1000 * 3600 * 24;
        long zero = System.currentTimeMillis() / tmp * tmp + tmp - TimeZone.getDefault().getRawOffset();    //明天零点零分零秒的毫秒数
        Date zeroTime = new Date(zero);
        logger.info(String.valueOf(new Timestamp(zero)));
        logger.info(String.valueOf(new Date().before(zeroTime)));
    }

    @Test
    public void strlength() {
        logger.info(String.valueOf(StringUtils.length("rojeralone")));
    }

    @Test
    public void equals() {
        String type = "image/png";
        logger.info(String.valueOf(type.equalsIgnoreCase("image/png")));
    }

    /**
     * 如果对象中的 map 不是 public 的，会无法序列化和反序列化
     * https://github.com/alibaba/fastjson/issues/1245
     */
    @Test
    public void jsonTest() {
        EventModel model = new EventModel(EventType.SEND_VALIDATE_EMAIL);
        String jsonStr = JSON.toJSONString(model);
        System.out.println("toJSONString" + jsonStr);
        System.out.println(JSON.parseObject(jsonStr, EventModel.class).getEventType());
        model.setExts("key", "value").setExts("key2", "value2");
        jsonStr = JSON.toJSONString(model);
        System.out.println("toJSONStringWithMap" + jsonStr);
        System.out.println(JSON.parseObject(jsonStr, EventModel.class).getExts("key"));
    }
}
