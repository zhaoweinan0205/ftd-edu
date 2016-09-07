package ftd.edu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @描述: .
 * @作者: ZHaoWeiNan .
 * @创建时间: 2016/8/29 .
 * @版本: 1.0 .
 */
public class JedisSentinelPoolDemo {

    public static void main(String[] args){
        Set<String> set = new HashSet<String>();
        set.add("112.33.2.156:36381");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("sentinel2",
                set);

        Jedis jedis = null;

        try {
            jedis = jedisSentinelPool.getResource();

            for (int i = 0 ; i < 100 ; i ++){
                jedis.set("key_" + i, "value_" + i);
            }

            System.out.println(jedis.get("key_7"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisSentinelPool != null){
                jedisSentinelPool.close();
            }
        }

        jedisSentinelPool.close();
    }
}
