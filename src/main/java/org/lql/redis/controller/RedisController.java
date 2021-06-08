package org.lql.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Title: RedisController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/7 20:42 <br>
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        // 这里值使用的是JDK的序列化器，所以Redis保存时不时整数，不能运算
        redisTemplate.opsForValue().set("int", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        // 使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);

        // 获取底层Jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        // 减1操作，这个命令RedisTemplate不支持，所以先获取底层的连接再操作
        jedis.decr("int");

        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");

        // 存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hash);

        // 新增一个字段
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");

        // 绑定散列操作的key，这样可以连续对同一个散列数据类型进行操作
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");

        // 删除两个字段
        hashOperations.delete("field1", "field2");

        // 新增一个字段
        hashOperations.put("field4", "value4");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);

        return map;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> testList() {
        // 插入两个列表，注意链表的顺序
        // 链表从左到右顺序为v10、v8、v6、v4、v2
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        // 链表从做到右顺序为v1、v2、v3、v4、v5、v6
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");

        // 绑定list2链表操作
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list2");

        // 从右边弹出一个成员
        Object result1 = listOperations.rightPop();
        System.out.println("result1:" + result1);

        // 获取定位元素，Redis从0开始计算，这里值为v2
        Object result2 = listOperations.index(1);
        System.out.println("result2:" + result2);

        // 从左边插入链表
        listOperations.leftPush("v0");

        // 求链表查毒
        Long size = listOperations.size();
        System.out.println("size:" + size);

        // 求链表下标区间成员，整个链表下标范围为0到size-1，这里不取最后一个元素
        List elements = listOperations.range(0, size - 2);
        System.out.println("elements:" + elements);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);

        return map;
    }

    @RequestMapping("/set")
    @ResponseBody
    public Map<String, Object> testSet() {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        BoundSetOperations setOperations = stringRedisTemplate.boundSetOps("set1");

        setOperations.add("v6", "v7");
        setOperations.remove("v1", "v7");
        Set set1 = setOperations.members();
        Long size = setOperations.size();

        // 交集
        Set inter = setOperations.intersect("set2");
        // 求交集，并且用新的集合inter保存
        setOperations.intersectAndStore("set2", "inter");

        // 差集
        Set diff = setOperations.diff("set2");
        setOperations.diffAndStore("set2", "diff");

        // 并集
        Set union = setOperations.union("set2");
        setOperations.unionAndStore("set2", "union");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);

        return map;
    }

    @RequestMapping("/zset")
    @ResponseBody
    public Map<String, Object> testZset() {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 1; i <=9; i++) {
            // 分数
            double score = i * 0.1;

            // 创建一个TypeTuple对象，存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
        BoundZSetOperations zSetOperations = stringRedisTemplate.boundZSetOps("zset1");

        zSetOperations.add("value10", 0.26);

        Set<String> setRange = zSetOperations.range(1, 6);

        // 按分数排序获取有序集合
        Set<String> setScore = zSetOperations.rangeByScore(0.2, 0.6);

        // 定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        // 大于
        range.gt("value3");
        // 大于等于
//        range.gte("value3");
        // 小于
//        range.lt("value3");
        // 小于等于
        range.lte("value8");
        // 按值排序，即按字符串排序
        Set<String> setLex = zSetOperations.rangeByLex(range);

        // 删除元素
        zSetOperations.remove("value9", "value2");

        // 求分数
        Double score = zSetOperations.score("value8");

        // 在下标区间下，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zSetOperations.rangeWithScores(1, 6);
        // 在分数区间下，按分数排序
        Set<ZSetOperations.TypedTuple<String>> screSet = zSetOperations.rangeByScoreWithScores(1, 6);
        // 按从大到小排序
        Set<String> reverseSet = zSetOperations.reverseRange(2, 8);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);

        return map;
    }

    @RequestMapping("/multi")
    @ResponseBody
    public Map<String, Object> testMulti() {

        redisTemplate.opsForValue().set("key1", "value1");

        List list = (List) redisTemplate.execute((RedisOperations operations) -> {
            // 设置要监控key1
            operations.watch("key1");
            // 开启事务，在exec命令执行前，全部都只是进入队列
            operations.multi();
            operations.opsForValue().set("key2", "value2");
            // 获取值姜维null，因为redis只是把命令放入队列
            Object value2 = operations.opsForValue().get("key2");
            System.out.println("命令在队列，所以value为null【" + value2 + "】");
            operations.opsForValue().set("key3", "value3");
            Object value3 = operations.opsForValue().get("key3");
            System.out.println("命令在队列，所以value为null【" + value3 + "】");
            // 执行exec命令，将先判别key1是否在监控后被修改过，如果是则不执行事务，否则就执行事务
            return operations.exec();
        });

        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);

        return map;
    }
}
