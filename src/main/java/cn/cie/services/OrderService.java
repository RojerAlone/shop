package cn.cie.services;

import cn.cie.utils.Result;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/8.
 */
public interface OrderService {

    /**
     * 创建新订单
     * @param uid
     * @param gids
     * @return
     */
    Result addOrders(int uid, List<Integer> gids);

    /**
     * 取消订单
     * @param orderid
     * @return
     */
    Result cancelOrder(int uid, int orderid);

    /**
     * 支付订单
     * @param uid
     * @param orderid
     * @return
     */
    Result pay(int uid, int orderid);

    /**
     * 获取自己所有的订单
     * @param uid
     * @return
     */
    @Deprecated
    Result getOrders(int uid);

    /**
     * 判断订单是否存在
     * @param orderid
     * @return
     */
    boolean exists(int orderid);

    /**
     * 获取待支付订单
     * @param uid
     * @return
     */
    Result getNotPayOrders(int uid);

    /**
     * 获取支付过的订单
     * @param uid
     * @return
     */
    Result getPaidOrders(int uid);

    /**
     * 获取取消的订单
     * @param uid
     * @return
     */
    Result getCancelOrders(int uid);

    /**
     * 自动取消15分钟还未支付的订单，定时器每1分钟检查一次
     */
    void autoCancelOrder();

}
