package cn.cie.entity;

import java.util.Date;

/**
 * 注册时发送到邮箱的验证码，用来验证用户邮箱
 */
public class Validatecode {

    /**
     * 验证码有效期，默认为10分钟
     */
    public static final int TIMEOUT = 60 * 10;

    private Integer uid;

    private String code;

    private Date ctime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}