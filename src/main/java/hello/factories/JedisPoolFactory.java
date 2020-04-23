package hello.factories;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

public class JedisPoolFactory {
    private static JedisPool pool;
    final private static String REDIS_HOST = "redis";

    public static JedisPool create() {
        if (pool == null) {
            System.out.println("CREATE POOL FACTORY");
            GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
            JedisPool _pool = new JedisPool(jedisPoolConfig, REDIS_HOST);
            pool = _pool;
        }

        return pool;
    }
}
