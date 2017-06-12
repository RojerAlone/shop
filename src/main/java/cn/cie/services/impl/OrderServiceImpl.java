package cn.cie.services.impl;

import cn.cie.mapper.OrderMapper;
import cn.cie.mapper.OrderitemMapper;
import cn.cie.mapper.OrdermapperMapper;
import cn.cie.services.OrderService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RojerAlone on 2017/6/12.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private OrdermapperMapper ordermapperMapper;

    public Result addOrders(int uid, List<Integer> gids) {
        return null;
    }

    public Result cancelOrder(int uid, int orderid) {
        return null;
    }

    public Result pay(int uid, int orderid) {
        return null;
    }

    public Result getOrders(int uid) {
        return null;
    }

    public Result getNotPayOrders(int uid) {
        return null;
    }

    public Result getPaidOrders(int uid) {
        return null;
    }

    public Result getCancelOrders(int uid) {
        return null;
    }
}
