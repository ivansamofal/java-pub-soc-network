package hello.services;

import hello.configuration.SpringRedisConfig;
import hello.dtos.RowDto;
import hello.entities.Row;
import hello.CsvParser;
import hello.factories.JedisPoolFactory;
import hello.singletons.RedissonBean;
import hello.singletons.SpringRedisBean;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.modelmapper.ModelMapper;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import hello.repositories.RowRepository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import org.redisson.api.RMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class RowService {
    final private RowRepository repository;

    public RowService(RowRepository rowRepository) {
        this.repository = rowRepository;
    }

    public void execute() {
        try {
            JedisPool _pool = JedisPoolFactory.create();
            Jedis jedis = _pool.getResource();
            jedis.set("some", "testJedis!");

            System.out.println(jedis.get("some"));
            Row row = repository.findById(1);

            /* ====== */

            RedissonClient redisson = RedissonBean.getClient();
            RMap<String, Row> map = redisson.getMap("myMap");
            map.put("key", row);
            Row mappedValue = map.get("key");
            System.out.println("mappedValue");
            System.out.println(mappedValue);

            /* ====== */


            /* ========== */
            ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
            ValueOperations<String, Object> values = SpringRedisBean.getSpringRedisClient();

            try {

                values.set("row", row);
                Row std = (Row) values.get("row");

                ModelMapper mapper = new ModelMapper();
                System.out.println("mapper.map(row, RowDto.class)");
                System.out.println(mapper.map(row, RowDto.class));

                System.out.println(std);
                System.out.println("TRY TO GET FROM REDIS!");

            } finally {
                ctx.close();
            }
            /* ========== */

            /* ========== */

//            Config config = new Config();
//            config.setTransportMode(TransportMode.EPOLL);
//            config.useClusterServers()
//                    // use "rediss://" for SSL connection
//                    .addNodeAddress("perredis://redis:6379");
//
//            RedissonClient redisson = Redisson.create(config);
//
//            RBucket<Row> bucket = redisson.getBucket("anyObject");
//// set an object
//            Row row = repository.findById(1);
//            bucket.set(row);
//// get an object
//            Row myObject = bucket.get();
//            System.out.println("myObject");
//            System.out.println(myObject);
//
//// supports some useful functions like:
//            bucket.trySet(row);
//            bucket.compareAndSet(row, myObject);
//
//            /* ========== */


            List<Row> _rows = CsvParser.parseProductCsv("/tmp/test.csv");

            repository.saveAll(_rows);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Row> findAll() {
        return (List<Row>) repository.findAll();
    }
}
