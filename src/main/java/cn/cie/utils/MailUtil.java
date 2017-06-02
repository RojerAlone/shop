package cn.cie.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by RojerAlone on 2017/6/2.
 */
public class MailUtil {

    private final static String USERNAME = "rojeralone@163.com";
    private final static String PASSWORD = "password";           // 需要打开SMTP并使用此授权码登录

    public static void sendMail(String user, String title, String content) {
        SimpleEmail email = new SimpleEmail();
        email.setCharset("UTF8");
        email.setHostName("smtp.163.com");
        email.setAuthentication(USERNAME, PASSWORD);
        try {
            email.setFrom(USERNAME);
            email.addTo(user);
            email.setSubject(title);
            email.setMsg(content);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
