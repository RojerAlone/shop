package cn.cie.controller;

import cn.cie.exception.NotFoundException;
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
        return "orderdetails";
    }

    @PostMapping(value = "order")
    @ResponseBody
    public Result order(@RequestParam(value = "games") Integer[] games) {
        return orderService.addOrders(userHolder.getUser().getId(), Arrays.asList(games));
    }

    @PostMapping(value = "{orderid}/cancel")
    @ResponseBody
    public Result cancelOrder(@PathVariable(value = "orderid") Integer orderid) {
        return orderService.cancelOrder(userHolder.getUser().getId(), orderid);
    }

    @GetMapping(value = "{orderid}/payway")
    public String payway(@PathVariable(value = "orderid") Integer orderid) {
        if (!orderService.exists(orderid)) {
            throw new NotFoundException();
        }
        this.getModel().addAttribute("id", orderid);
        return "order_1";
    }

    @GetMapping(value = "{orderid}/pay")
    public String pay(@PathVariable(value = "orderid") Integer orderid) {
        if (!orderService.exists(orderid)) {
            throw new NotFoundException();
        }
        this.getModel().addAttribute("id", orderid);
        return "order_2";
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
