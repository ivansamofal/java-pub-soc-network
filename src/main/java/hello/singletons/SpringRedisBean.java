package hello.singletons;

import hello.configuration.SpringRedisConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class SpringRedisBean {
    private static ValueOperations<String, Object> client;

    public static ValueOperations<String, Object> getSpringRedisClient() {
        if (client == null) {
            ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
            @SuppressWarnings("unchecked")
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ctx.getBean("redisTemplate");
            client = redisTemplate.opsForValue();
        }

        return client;
    }
}
