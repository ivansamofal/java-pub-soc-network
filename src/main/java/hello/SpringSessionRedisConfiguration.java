package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
//@PropertySource("application.properties")
//public class SpringSessionRedisConfiguration {
//
//    @Value("${redis.hostname}")
//    private String redisHostName;
//
//    @Value("${redis.port}")
//    private int redisPort;
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer    propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(redisHostName);
//        factory.setPort(redisPort);
//        factory.setUsePool(true);
//        return factory;
//    }
//
//    @Bean
//    RedisTemplate<Object, Object> redisTemplate() {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        return redisTemplate;
//    }
//
//    @Bean
//    RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//        return redisCacheManager;
//    }
//}
