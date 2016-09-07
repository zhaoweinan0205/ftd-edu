package ftd.edu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @描述: .
 * @作者: ZHaoWeiNan .
 * @创建时间: 2016/8/29 .
 * @版本: 1.0 .
 */
public class JedisPoolDemo {

    public static void main(String[] args) {
        // 构建连接池配置信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(50);

        // 构建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "112.33.2.156", 36381);

        // 从连接池中获取连接
        Jedis jedis = jedisPool.getResource();

        // 读取数据
        System.out.println(jedis.get("mytest"));

        // 将连接还回到连接池中
        jedisPool.returnResource(jedis);

        // 释放连接池
        jedisPool.close();

    }
}
