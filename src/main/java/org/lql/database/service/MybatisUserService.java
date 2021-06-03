package org.lql.database.service;

import org.lql.database.mybatis.mybatisdomain.User;

/**
 * Title: MybatisUserService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 14:14 <br>
 */
public interface MybatisUserService {

    User getUser(Long id);
}
