package cn.cie.controller;

import cn.cie.services.OrderService;
import cn.cie.utils.Result;
import cn.cie.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by RojerAlone on 2017/6/12.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/order")
public class OrderController extends AbstractController{

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserHolder userHolder;

    @GetMapping(value = "orders")
    public String orders() {
        return "orders";
    }

    @PostMapping(value = "order")
    @ResponseBody
    public Result order(Integer[] games) {
        return orderService.addOrders(userHolder.getUser().getId(), Arrays.asList(games));
    }

    @PostMapping(value = "{orderid}/cancel")
    @ResponseBody
    public Result cancelOrder(@PathVariable(value = "orderid") Integer orderid) {
        return orderService.cancelOrder(userHolder.getUser().getId(), orderid);
    }

    @PostMapping(value = "{orderid}/pay")
    @ResponseBody
    public Result payOrder(@PathVariable(value = "orderid") Integer orderid) {
        return orderService.pay(userHolder.getUser().getId(), orderid);
    }

    @PostMapping(value = "notpay")
    @ResponseBody
    public Result getNotPayOrders() {
        return orderService.getNotPayOrders(userHolder.getUser().getId());
    }

    @PostMapping(value = "paid")
    @ResponseBody
    public Result getPaidOrders() {
        return orderService.getPaidOrders(userHolder.getUser().getId());
    }

    @PostMapping(value = "cancel")
    @ResponseBody
    public Result getCancelOrders() {
        return orderService.getCancelOrders(userHolder.getUser().getId());
    }

}
