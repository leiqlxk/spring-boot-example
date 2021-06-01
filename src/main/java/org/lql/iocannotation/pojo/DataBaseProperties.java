package org.lql.iocannotation.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Title: DataBaseProperties <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 14:24 <br>
 */
@Component
public class DataBaseProperties {

    @Value("${database.driver}")
    private String driverName = null;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String userName;

    @Value("${database.password}")
    private String password;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        System.out.println(driverName);
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        System.out.println(url);
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println(userName);
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println(password);
        this.password = password;
    }
}
