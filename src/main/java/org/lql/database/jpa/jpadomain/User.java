package org.lql.database.jpa.jpadomain;

import org.lql.database.enumeration.SexConverter;
import org.lql.database.enumeration.SexEnum;

import javax.persistence.*;

/**
 * Title: User <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 11:28 <br>
 */
// 标明是一个实体
@Entity(name = "user")
// 定义映射的表
@Table(name = "t_user_boot")
public class User {

    // 标明主键
    @Id
    // 主键策略，递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 定义属性和表的映射关系
    @Column(name = "user_name")
    private String userName;

    // 定义转换器
    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    //属性名和表字段名一致就能自动映射而不需要@Column
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

    public SexEnum getSexEnum() {
        return sex;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sex = sexEnum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String not) {
        this.note = note;
    }
}
