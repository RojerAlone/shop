package cn.cie.common.utils;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by RojerAlone on 2017/6/2.
 */
public class MailUtilTest {
    @Test
    public void sendMail() throws Exception {
        String name = "1091165843@qq.com";
        String title = "title";
        String content = "test";
        MailUtil.sendMail(name, title, content);

    }

    @Test
    public void UUIDTest() {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void changeSql() {
        String sql = "INSERT INTO `tagmapper` VALUES (2, 46);\n";
        sql = sql.replaceAll("INSERT INTO `tagmapper` VALUES", ",");
        sql = sql.replaceAll(";", "");
        sql = sql.replaceAll("\n", "");
        System.out.println(sql);
    }

    @Test
    public void createSql() {
        for (int i = 1 ; i < 32 ; ++i) {
            for (int j = 1 ; j < 6 ; ++j) {
                System.out.print("(" + i + ", " + j + "), ");
            }
        }
    }

}