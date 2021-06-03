package org.lql.database.mybatis.mybatisdao;

import org.lql.database.mybatis.mybatisdomain.User;
import org.springframework.stereotype.Repository;

/**
 * Title: MyBatisUserDao <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 13:48 <br>
 */
@Repository
public interface MyBatisUserDao {

    public User getUser(Long id);

}
