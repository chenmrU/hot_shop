package com.cmr.hotshop.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chenmengrui
 * @Description: 定时任务：订单超时取消
 * @date 2019/12/28 10:58
 */
@Slf4j
@Component
public class OrderTimeOutCancelTask {

    @Scheduled(cron = "0 0/10 * ? * ?")
    public void cancelTimeOutOrder() {
        log.info("取消订单");
    }

}
