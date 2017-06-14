package cn.cie.entity;

import java.util.Date;

/**
 * 游戏
 */
public class Game {

    /**
     * 未上架
     */
    public static final Byte STAT_PRE = 0;
    /**
     * 已上架
     */
    public static final Byte STAT_OK = 1;
    /**
     * 已下架
     */
    public static final Byte STAT_DEL = 2;

    private Integer id;

    private String creater;

    private String name;

    private String desc;

    private String systemcfg;

    private Double price;

    private Double discount;

    private Date ctime;

    private Date utime;

    private Byte stat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSystemcfg() {
        return systemcfg;
    }

    public void setSystemcfg(String systemcfg) {
        this.systemcfg = systemcfg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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
}