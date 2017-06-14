package cn.cie.entity;

/**
 * 订单-订单详情映射
 */
public class Ordermapper {
    private Integer order;

    private Integer item;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }
}