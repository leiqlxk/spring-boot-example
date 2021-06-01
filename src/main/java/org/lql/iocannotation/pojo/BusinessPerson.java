package org.lql.iocannotation.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Title: BusinessPerson <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 11:44 <br>
 */
@Component
public class BusinessPerson implements Person, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    // 默认按照类型注入
    /*@Autowired
    private Animal animal;*/

    // 首先根据类型查找对应的bean，如果对应类型的bean不是唯一，那么它会根据属性名称和bean的名称进行匹配。如果匹配上了就会使用该bean，如果还无法匹配就会抛出异常
    // @Autowired(require = false)
    /*@Autowired
    private Animal dog;*/

    // 和@Primary搭配使用
    /*@Autowired
    private Animal animal;*/

    // 和@Qualifier搭配直接指定按类型和名称查找
   /* @Autowired
    @Qualifier("cat")*/
    private Animal animal;

    @Autowired
    private List<Animal> animals;

    public void service() {
        this.animal.use();
    }

    @Autowired
    @Qualifier("cat")
    public void setAnimal(Animal animal) {
        System.out.println("setAnimal");
        this.animal = animal;
    }

    public void allService() {
        for (Animal animal : animals) {
            animal.use();
        }
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    public void setBeanName(String s) {
        System.out.println("setBeanName");
    }

    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    @PreDestroy
    public void destory1() {
        System.out.println("destory1");
    }



}
