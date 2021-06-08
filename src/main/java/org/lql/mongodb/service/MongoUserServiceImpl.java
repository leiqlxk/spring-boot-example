package org.lql.mongodb.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title: MongoUserServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/8 14:47 <br>
 */
@Service
public class MongoUserServiceImpl implements MongoUserService {

    /*@Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        // 使用名称为user文档保存用户信息
        mongoTemplate.save(user, "user");
        // 如果文档采用类名首字符小写，则可以如下保存
//        mongoTemplate.save(user);
    }

    @Override
    public DeleteResult deleteUser(Long id) {
        Criteria criteria = Criteria.where("id").is(id);

        Query query = Query.query(criteria);
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        return deleteResult;
    }

    @Override
    public List<User> findUser(String userName, String note, int skip, int limit) {
        // 将用户名称和备注设置为模糊查询准则
        Criteria criteria = Criteria.where("userName").regex(userName).and("note").regex(note);

        // 构建查询条件，并设置分页跳过前skip个，至多返回limit个
        Query query = Query.query(criteria).limit(limit).skip(skip);

        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;
    }

    @Override
    public UpdateResult updateUser(Long id, String userName, String note) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);

        // 定义更新对象，后续可变化的字符串代表排除在外的属性
        Update update = Update.update("userName", userName);
        update.set("note", note);

        // 更新第一个文档
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);

        // 更新多个文档
//        updateResult = mongoTemplate.updateMulti(query, update, User.class);
        return updateResult;
    }

    @Override
    public User getUser(Long id) {
        return mongoTemplate.findById(id, User.class);
    }*/
}
