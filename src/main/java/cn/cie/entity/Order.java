package cn.cie.entity;

import java.util.Date;

/**
 * 订单
 */
public class Order {

    /**
     * 未支付
     */
    public static final Byte STAT_NOT_PAY = 0;
    /**
     * 已支付
     */
    public static final Byte STAT_PAY = 1;
    /**
     * 取消
     */
    public static final Byte STAT_CANCEL = 2;

    private Integer id;

    private Integer uid;

    private Double price;

    private Date ctime;

    private Date utime;

    private Byte stat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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