package org.lql.other.scheduling.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Title: ScheduleServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 14:14 <br>
 */
@Service
public class ScheduleServiceImpl {

    // 计数器
    int count1 = 1;
    int count2 = 1;

    @Scheduled(fixedRate = 1000)
    @Async
    public void job1() {
        System.out.println("【" + Thread.currentThread().getName() + "】【job1】每秒钟执行一次，执行第【" + count1 + "】");
        count1++;
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void job2() {
        System.out.println("【" + Thread.currentThread().getName() + "】【job2】每秒钟执行一次，执行第【" + count2 + "】");
        count2++;
    }
}
