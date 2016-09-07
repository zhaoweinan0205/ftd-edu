package ftd.edu.redis;

import redis.clients.jedis.Jedis;

/**
 * @描述: .
 * @作者: ZHaoWeiNan .
 * @创建时间: 2016/8/29 .
 * @版本: 1.0 .
 */
public class JedisDemo {

    public static void main(String[] args) {
        // 构造jedis对象
        Jedis jedis = new Jedis("112.33.2.156", 36381);
        // 向redis中添加数据
        jedis.set("mytest", "123");
        // 从redis中读取数据
        String value = jedis.get("mytest");

        System.out.println(value);
        // 关闭连接
        jedis.close();

    }
}
