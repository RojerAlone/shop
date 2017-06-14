package cn.cie.entity;

import java.util.Date;

/**
 * token，用来标识一个用户
 */
public class Token {

    /**
     * 正常状态
     */
    public static final Byte STAT_OK = 0;
    /**
     * token已过期
     */
    public static final Byte STAT_EXPIRED = 1;

    private Integer uid;

    private String token;

    private Date expiredTime;

    private String ip;

    private String device;

    private Date ctime;

    private Byte stat;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Byte getStat() {
        return stat;
    }

    public void setStat(Byte stat) {
        this.stat = stat;
    }
}