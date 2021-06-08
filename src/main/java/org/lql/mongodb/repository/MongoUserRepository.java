package org.lql.mongodb.repository;

import org.lql.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title: MongoUserRepository <br>
 * ProjectName: spring-boot-example <br>
 * description: 使用jpa编程操作MongoDB <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/8 15:27 <br>
 */
@Repository
public interface MongoUserRepository extends MongoRepository<User, Long> {

    /**
     * description: 符合JPA规范命名方法，则不需要再实现该方法也可用 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/8 15:29 <br>
     * 
     * @throws
     * @param userName
     * @return java.util.List<org.lql.mongodb.domain.User>
     */ 
    List<User> findByUserNameLike(String userName);

    /**
     * description: 自定义查询 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/8 15:32 <br>
     *
     * @throws
     * @param id
     * @param userName
     * @return org.lql.mongodb.domain.User
     */
    @Query("{'id': ?0, 'userName': ?1}")
    User find(Long id, String userName);
}
