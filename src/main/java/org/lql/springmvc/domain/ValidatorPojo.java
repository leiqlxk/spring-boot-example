package org.lql.springmvc.domain;

import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Title: Validator <br>
 * ProjectName: spring-boot-example <br>
 * description: jsr-303注解验证 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 17:57 <br>
 */
public class ValidatorPojo {

    // 非空判断
    @NotNull(message = "id不能为空")
    private Long id;

    @NotNull
    @Future(message = "需要一个将来日期")
//    @Past(message = "只能是过去的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000.00")
    private Double doubleValue;

    @Min(value = 1, message = "最小值为1")
    @Max(value = 88, message = "最大值为88")
    private Integer integer;

    @Range(min = 1, max = 888, message = "范围为1至888")
    private Long range;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求20到30之间")
    private String size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
