package org.lql.security.service;

import org.lql.security.domain.DatabaseRole;
import org.lql.security.domain.DatabaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: UserDetailsServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: 自定义用户认证服务 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/11 14:21 <br>
 */
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 获取数据库用户信息
        DatabaseUser user = userRoleService.getUserByName(userName);

        // 获取数据库角色信息
        List<DatabaseRole> roles = userRoleService.findRolesByUserName(userName);

        // 将信息转换为UserDetails对象
        return changToUser(user, roles);
    }

    private UserDetails changToUser(DatabaseUser user, List<DatabaseRole> roles) {

        // 权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // 赋予查询到的角色
        for(DatabaseRole role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }

        // 创建userDetails对象，设置用户名、密码和权限
        UserDetails userDetails = new User(user.getUserName(), user.getPwd(), authorityList);

        return userDetails;
    }
}
