package org.lql.security.service;

import org.lql.security.domain.DatabaseRole;
import org.lql.security.domain.DatabaseUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: UserRoleServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/11 14:23 <br>
 */
//@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Override
    public DatabaseUser getUserByName(String userName) {
        return null;
    }

    @Override
    public List<DatabaseRole> findRolesByUserName(String userName) {
        return null;
    }
}
