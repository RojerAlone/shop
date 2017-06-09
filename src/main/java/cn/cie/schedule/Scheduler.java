package cn.cie.schedule;

import cn.cie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

/**
 * Created by RojerAlone on 2017/6/9.
 * 定时任务
 */
@Component
public class Scheduler {

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 20000)
    public void delExpiredValidatecode() {
        userService.delValidatecode();
    }

}
