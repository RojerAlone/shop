package cn.cie.controller;

import cn.cie.services.AdminService;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by RojerAlone on 2017/6/15.
 */
@CrossOrigin
@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "login")
    public String login() {
        return "adminlogin";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Result login(String username, String password, HttpServletResponse response) {
        Result result = adminService.login(username, password);
        if (result.isSuccess()) {
            Cookie cookie = new Cookie("token", (String) result.getData());
            cookie.setPath("/");
            response.addCookie(cookie);
            return Result.success();
        }
        return result;
    }

    @GetMapping(value = "home")
    public String adminPage() {
        return "adminpage";
    }

    @PostMapping(value = "getuser")
    @ResponseBody
    public Result getUser(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        return adminService.getUser(page);
    }

    @PostMapping(value = "restrictuser")
    @ResponseBody
    public Result restrict(Integer uid) {
        return adminService.restrict(uid);
    }

    @PostMapping(value = "relieveuser")
    @ResponseBody
    public Result relieve(Integer uid) {
        return adminService.relieve(uid);
    }

    @PostMapping(value = "deluser")
    @ResponseBody
    public Result delUser(Integer uid) {
        return adminService.delete(uid);
    }

    @PostMapping(value = "getgames")
    @ResponseBody
    public Result getGames(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        return adminService.getGames(page);
    }

    @PostMapping(value = "updategamekind")
    @ResponseBody
    public Result updateGameKind(Integer game, Integer[] kinds) {
        return adminService.updateGameKind(game, Arrays.asList(kinds));
    }

    @PostMapping(value = "upgame")
    @ResponseBody
    public Result upGame(Integer game, @RequestParam(value = "date", required = false) Date date) {
        return adminService.upGame(game, date);
    }

    @PostMapping(value = "downgame")
    @ResponseBody
    public Result downGame(Integer game) {
        return adminService.downGame(game);
    }

    @PostMapping(value = "addkind")
    @ResponseBody
    public Result addKind(String kind) {
        return adminService.addKind(kind);
    }

}