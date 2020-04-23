package hello.singletons;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonBean {
    private static RedissonClient redissonClient;
    final private static String REDIS_CONFIG = "redis://redis:6379";

    public static RedissonClient getClient() {
        if (redissonClient == null) {
            Config config = new Config();
            config.useSingleServer().setAddress(REDIS_CONFIG);
            redissonClient = Redisson.create(config);
        }

        return redissonClient;
    }
}
