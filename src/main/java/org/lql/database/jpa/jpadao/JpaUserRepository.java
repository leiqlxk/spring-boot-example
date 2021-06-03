package org.lql.database.jpa.jpadao;

import org.lql.database.jpa.jpadomain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Title: JpaUserRepository <br>
 * ProjectName: spring-boot-example <br>
 * description: 使用jpa时通常我们只需要扩展 JpaRepository接口即可获得JPA提供的方法<br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 11:39 <br>
 */
public interface JpaUserRepository extends JpaRepository<User, Long> {

    // 使用jpa查询语言 user 及userName是对应实体类中的命名
    @Query("from user where userName like concat('%', ?1, '%') and note like concat('%', ?2, '%') ")
    public List<User> findUsers(String userName, String note);

    // JPA的命名查询一般使用get/find开头
    List<User> findByUserNameLike(String userName);

    User getUserById(Long id);

    List<User> findByUserNameLikeOrNoteLike(String userName, String note);
}
