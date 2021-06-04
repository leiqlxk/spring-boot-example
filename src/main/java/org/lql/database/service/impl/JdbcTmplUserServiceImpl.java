package org.lql.database.service.impl;

import org.lql.database.jdbcdomain.User;
import org.lql.database.enumeration.SexEnum;
import org.lql.database.service.JdbcTmplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Title: JdbcTmplUserServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 10:46 <br>
 */
@Service
public class JdbcTmplUserServiceImpl implements JdbcTmplUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User getUser(Long id) {
        String sql = "select * from t_user_root where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, getUserMapper());
    }

    // jdbcTemplate每执行一次sql操作都会从数据源获取一条连接，执行完后会关闭连接，
    // 如果我们想多个操作在同一个连接中完成可以是用StatementCallback或者ConnectionCallback接口实现回调
    @Override
    public User getUser2(Long id) {
        User result = this.jdbcTemplate.execute((Statement stmt) -> {
            String sql = "select count(*) total from t_user_boot where id = " + id;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int total = rs.getInt("total");
                System.out.println(total);
            }

            String sql1 = "select id, user_name, sex, note from t_user_boot where id = " + id;
            ResultSet rs1 = stmt.executeQuery(sql1);
            User user = null;
            while (rs1.next()) {
                int rowNum = rs1.getRow();
                user = getUserMapper().mapRow(rs1, rowNum);
            }

            return user;
        });

        return result;
    }

    @Override
    public User getUser3(Long id) {
        return this.jdbcTemplate.execute((Connection conn) -> {
            String sql = "select count(*) total from t_user_boot where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int total = rs.getInt("total");
                System.out.println(total);
            }

            String sql1 = "select id, user_name, sex, note from t_user_boot where id = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setLong(1, id);
            ResultSet rs1 = stmt1.executeQuery();
            User user = null;
            while (rs1.next()) {
                int rowNum = rs1.getRow();
                user = getUserMapper().mapRow(rs1, rowNum);
            }

            return user;
        });
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = "select * from t_user_root where user_name like concat('%', ?, '%') and note like concat('%', ?, '%')";
        return jdbcTemplate.query(sql, new Object[]{userName, note}, getUserMapper());
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user_boot(user_name, sex, note) values(?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSexEnum().getId(), user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user_boot set user_name = ?, sex = ?, note = ? where id = ?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSexEnum().getId(), user.getNote(), user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user_boot where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // 获取映射关系
    private RowMapper<User> getUserMapper() {
        // 使用Lambda表达式创建用户映射关系
        RowMapper<User> userRowMapper = (ResultSet rs, int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setNote(rs.getString("note"));
            int sexId = rs.getInt("sex");
            SexEnum sexEnum = SexEnum.getEnumById(sexId);
            user.setSexEnum(sexEnum);
            return user;
        };

        return userRowMapper;
    }
}
