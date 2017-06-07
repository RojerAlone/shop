package cn.cie.entity.dto;

import cn.cie.entity.Game;
import cn.cie.entity.Kind;
import cn.cie.entity.Tag;

import java.util.Date;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/6.
 */
public class GameDTO {

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

    private List<Kind> kinds;

    private List<Tag> tags;

    public GameDTO() {}

    public GameDTO(Game game, List<Tag> tags) {
        this.id = game.getId();
        this.creater = game.getCreater();
        this.name = game.getName();
        this.desc = game.getDesc();
        this.systemcfg = game.getSystemcfg();
        this.price = game.getPrice();
        this.discount = game.getDiscount();
        this.ctime = game.getCtime();
        this.utime = game.getUtime();
        this.stat = game.getStat();
        this.tags = tags;
    }

    public GameDTO(Game game, List<Kind> kinds, List<Tag> tags) {
        this.id = game.getId();
        this.creater = game.getCreater();
        this.name = game.getName();
        this.desc = game.getDesc();
        this.systemcfg = game.getSystemcfg();
        this.price = game.getPrice();
        this.discount = game.getDiscount();
        this.ctime = game.getCtime();
        this.utime = game.getUtime();
        this.stat = game.getStat();
        this.kinds = kinds;
        this.tags = tags;
    }

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

    public List<Kind> getKinds() {
        return kinds;
    }

    public void setKinds(List<Kind> kinds) {
        this.kinds = kinds;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
