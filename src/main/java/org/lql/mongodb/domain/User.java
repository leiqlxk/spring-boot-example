package org.lql.mongodb.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Title: User <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/8 14:32 <br>
 */
// 标识为MongoDB文档
//@Document
public class User implements Serializable {

    // MongoDB文档编号， 主键
    @Id
    private Long id;

    // 在MongoDB中使用user_name保存属性
//    @Field("user_name")
    private String userName;

    private String note;

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
