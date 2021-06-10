package org.lql.database.mybatis.mybatisdao;

import org.apache.ibatis.annotations.Param;
import org.lql.database.mybatis.mybatisdomain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    int insertUser(User user);

    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);

    void updateUser(User user);

    int deletUser(Long id);
}
