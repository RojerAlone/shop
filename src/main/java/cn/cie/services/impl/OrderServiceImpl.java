package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Order;
import cn.cie.entity.Orderitem;
import cn.cie.entity.Ordermapper;
import cn.cie.entity.dto.OrderDTO;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.OrderMapper;
import cn.cie.mapper.OrderitemMapper;
import cn.cie.mapper.OrdermapperMapper;
import cn.cie.services.OrderService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private GameMapper gameMapper;
    @Autowired
    private OrdermapperMapper ordermapperMapper;

    @Transactional
    public Result addOrders(int uid, List<Integer> gids) {
        Order order = new Order();
        order.setUid(uid);
        orderMapper.insert(order);
        double total = 0;
        List<Orderitem> orderitems = new ArrayList<Orderitem>();
        // 对所有的游戏进行存储、计价
        for (Integer gid : gids) {
            Game game = gameMapper.selectById(gid);
            if (game.getStat() != Game.STAT_OK) {
                return Result.fail(MsgCenter.ERROR_PARAMS);
            }
            Orderitem item = new Orderitem();
            item.setGid(gid);
            // 如果现价不为空，那么就计算现价
            if (game.getDiscount() != null) {
                total += game.getDiscount();
                item.setPrice(game.getDiscount());
            } else {
                total += game.getPrice();
                item.setPrice(game.getPrice());
            }
            orderitemMapper.insert(item);
            Ordermapper mapper = new Ordermapper();
            mapper.setItem(item.getId());
            mapper.setOrder(order.getId());
            ordermapperMapper.insert(mapper);
        }
        // 更新所有游戏的价格
        order.setPrice(total);
        orderMapper.update(order);
        return Result.success(order);
    }

    public Result cancelOrder(int uid, int orderid) {
        Order order = orderMapper.selectById(orderid);
        // 如果orderid错误或者用户与订单不匹配，就返回错误
        if (order == null || order.getUid() != uid) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        order.setStat(Order.STAT_CANCEL);
        if (1 == orderMapper.update(order)) {
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    public Result pay(int uid, int orderid) {
        return null;
    }

    public Result getOrders(int uid) {
        return null;
    }

    public Result getNotPayOrders(int uid) {
        return Result.success(parseOrderByStat(uid, Order.STAT_NOT_PAY));
    }

    public Result getPaidOrders(int uid) {
        return Result.success(parseOrderByStat(uid, Order.STAT_NOT_PAY));
    }

    public Result getCancelOrders(int uid) {
        return Result.success(parseOrderByStat(uid, Order.STAT_NOT_PAY));
    }

    public void autoCancelOrder() {

    }

    private List<OrderDTO> parseOrderByStat(Integer uid, Byte stat) {
        List<OrderDTO> res = new ArrayList<OrderDTO>();

        return res;
    }
}
