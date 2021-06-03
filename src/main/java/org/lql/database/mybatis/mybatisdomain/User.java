package org.lql.database.mybatis.mybatisdomain;

import org.apache.ibatis.type.Alias;
import org.lql.database.enumeration.SexEnum;

/**
 * Title: User <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 13:32 <br>
 */
// mybatis指定别名
@Alias("user")
public class User {

    private Long id;

    private String userName;

    // 性别枚举，这里需要使用typeHandler进行转换
    private SexEnum sex;

    private String note;

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

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
