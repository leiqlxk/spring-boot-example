package org.lql.iocannotation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Title: SpelExpression <br>
 * ProjectName: spring-boot-example <br>
 * description: SpEl表达式 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 9:58 <br>
 */
@Component
public class SpelExpressionTest {

    // ${....}代表占位符，其会读取上下文的属性值装配到属性中，这是一个最简单的Spring表达式
    @Value("${database.driverName}")
    private String driver;

    // 此处使用#{.....}代表启用Spring表达式，它具有运算的功能；T(....)代表引入类，System为java.lang包中的类这是java默认加载的包因此无需写全限定名，如果是其他的包需要写全限定名才能引用
    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime;

    // 直接赋值
    @Value("#{'使用Spring EL赋值字符串'}")
    private String str;

    @Value("#{9.3E3}")
    private double d;

    @Value("#{3.14}")
    private float f;

    // 引用Spring bean的属性来赋值
    @Value("#{spelExpressionTest.str?.toUpperCase()}")
    private String otherBeanProp;

    // 数学运算
    @Value("#{1 + 2}")
    private int run;

    // 浮点比较
    @Value("#{spelExpressionTest.f == 3.14f}")
    private boolean piFlag;

    // 字符串比较
    @Value("#{spelExpressionTest.str eq 'Spring Boot'}")
    private boolean strFlag;

    // 字符串连接
    @Value("#{spelExpressionTest.str + '连接字符串'}")
    private String strApp;

    // 三元运算
    @Value("#{spelExpressionTest.d > 1000 ? '大于' : '小于'}")
    private String resultDesc;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("简单表达式driver:" + this.driver)
                .append("函数调用initTime:" + this.initTime)
                .append("直接赋值str:" + this.str)
                .append("直接赋值d:" + this.d)
                .append("直接赋值f:" + this.f)
                .append("引用Spring bean的属性来赋值otherBeanProp:" + this.otherBeanProp)
                .append("数学运算run:" + this.run)
                .append("浮点比较piFlag:" + this.piFlag)
                .append("字符串比较strFlag:" + this.strFlag)
                .append("三元运算resultDesc:" + this.resultDesc)
                .toString();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Long getInitTime() {
        return initTime;
    }

    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public String getOtherBeanProp() {
        return otherBeanProp;
    }

    public void setOtherBeanProp(String otherBeanProp) {
        this.otherBeanProp = otherBeanProp;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public boolean isPiFlag() {
        return piFlag;
    }

    public void setPiFlag(boolean piFlag) {
        this.piFlag = piFlag;
    }

    public boolean isStrFlag() {
        return strFlag;
    }

    public void setStrFlag(boolean strFlag) {
        this.strFlag = strFlag;
    }

    public String getStrApp() {
        return strApp;
    }

    public void setStrApp(String strApp) {
        this.strApp = strApp;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
