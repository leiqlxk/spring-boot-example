package org.lql.other.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Title: AsyncServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 13:20 <br>
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    // 声明使用异步调用
    @Async
    public void generateReport() {
        System.out.println("报表线程名称：【" + Thread.currentThread().getName() + "】");
    }
}
