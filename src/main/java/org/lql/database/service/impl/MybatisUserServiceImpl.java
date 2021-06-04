package org.lql.database.service.impl;

import org.lql.database.mybatis.mybatisdao.MyBatisUserDao;
import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: MybatisUserServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 14:15 <br>
 */
@Service
public class MybatisUserServiceImpl implements MybatisUserService {

    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }

    @Override
    // 声明式事务
//    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int insertUser(User user) {
        return myBatisUserDao.insertUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertUsers(List<User> users) {
        int count = 0;
        for (User user : users) {
            count += this.insertUser(user);
        }
        return count;
    }
}
