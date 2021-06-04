package org.lql.database.service.impl;

import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.lql.database.service.UserBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: UserBatchServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/4 14:38 <br>
 */
@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private MybatisUserService mybatisUserService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertUsers(List<User> users) {
        int count = 0;
        for (User user : users) {
            count += mybatisUserService.insertUser(user);
        }
        return count;
    }
}
