package org.lql.database.mybatis.config;

/**
 * Title: MybatisConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: 装配mybatis接口 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 14:08 <br>
 */
//@Configuration
public class MybatisConfig {

    /*@Autowired
    SqlSessionFactory sqlSessionFactory;*/

    // 使用MapperFactoryBean定义一个mybatis的mapper接口
   /* @Bean
    public MapperFactoryBean<MyBatisUserDao> initMybatisUserDao() {
        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<MyBatisUserDao>();
        bean.setMapperInterface(MyBatisUserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);

        return bean;
    }*/

  /* @Bean
   public MapperScannerConfigurer mapperScannerConfig() {
       // 定义扫描器实例
       MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
       // 加载sqlSessionFactory，spring boot会自动生产SqlSessionFactory实例
       mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
       // 定义扫描包
       mapperScannerConfigurer.setBasePackage("org.lql.database.mybatis.mybatisdao.*");
       // 限定被标注@Repository的接口才被扫描
       mapperScannerConfigurer.setAnnotationClass(Repository.class);
       // 通过继承某个接口限制扫描，一般不使用
//       mapperScannerConfigurer.setMarkerInterface(......);
       return mapperScannerConfigurer;
   }*/
}
