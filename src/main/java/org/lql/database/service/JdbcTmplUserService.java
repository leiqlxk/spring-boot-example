package org.lql.database.service;

import org.lql.database.jdbcdomain.User;

import java.util.List;

/**
 * Title: JdbcTmplUserService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 10:44 <br>
 */
public interface JdbcTmplUserService {

    public User getUser(Long id);

    public List<User> findUsers(String userName, String note);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(Long id);

    public User getUser2(Long id);

    public User getUser3(Long id);
}
