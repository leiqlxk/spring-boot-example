package org.lql.database.enumeration;

import javax.persistence.AttributeConverter;

/**
 * Title: SexConverter <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 11:32 <br>
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer> {

    // 将枚举转换为数据库列
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    // 将数据库列转换为枚举
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
