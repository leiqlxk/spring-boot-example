package org.lql.security.service;

import org.lql.security.domain.DatabaseRole;
import org.lql.security.domain.DatabaseUser;

import java.util.List;

/**
 * Title: UserRoleService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/11 14:22 <br>
 */
public interface UserRoleService {
    DatabaseUser getUserByName(String userName);

    List<DatabaseRole> findRolesByUserName(String userName);
}
