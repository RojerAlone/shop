package cn.cie.utils;

import org.junit.Test;

import static org.junit.Assert.*;

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

}