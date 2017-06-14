package cn.cie.entity;

import java.util.Date;

public class User {

    /**
     * 未验证，需要邮箱验证
     */
    public static final Byte STAT_NOT_VALIDATE = 0;
    /**
     * 正常
     */
    public static final Byte STAT_OK = 1;
    /**
     * 受限
     */
    public static final Byte STAT_RESTRICT = 2;
    /**
     * 已删除
     */
    public static final Byte STAT_DEL = 3;

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private Long phone;

    private Date ctime;

    private Date utime;

    private Byte stat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Byte getStat() {
        return stat;
    }

    public void setStat(Byte stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", ctime=" + ctime +
                ", utime=" + utime +
                ", stat=" + stat +
                '}';
    }
}