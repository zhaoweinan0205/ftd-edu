package ftd.edu.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述: .
 * @作者: ZHaoWeiNan .
 * @创建时间: 2016/8/29 .
 * @版本: 1.0 .
 */
public class ShardedJedisPoolDemo {
    public static void main(String[] args) {
        // 构建连接池配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 设置最大连接数
        poolConfig.setMaxTotal(50);

        // 定义集群信息
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();


        shards.add(new JedisShardInfo("112.33.2.156", 36381));
        //shards.add(new JedisShardInfo("112.33.2.156", 36380));

        // 定义集群连接池
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, shards);
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            for (int i = 0; i < 100; i++) {
                shardedJedis.set("key_" + i, "value_" + i);
            }
            System.out.println(shardedJedis.get("key_7"));
            // 从redis中获取数据
            // String value = shardedJedis.get("mytest");
            // System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }

        // 关闭连接池
        shardedJedisPool.close();

    }

}
