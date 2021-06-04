package org.lql.database.service;

import org.lql.database.mybatis.mybatisdomain.User;

import java.util.List;

/**
 * Title: UserBatchService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/4 14:37 <br>
 */
public interface UserBatchService {

    int insertUsers(List<User> users);
}
