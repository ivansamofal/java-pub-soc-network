package hello.services;

import com.google.gson.Gson;
import hello.constants.KafkaTopic;
import hello.dtos.UserInputDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    final private Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);
    final private UserService userService;

    public KafkaConsumerServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void consumeUser() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "kafka1:9092");
        props.setProperty("group.id", "consumer1");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaTopic.USER));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

                String json = record.value();
                Gson gson = new Gson();
                UserInputDto userInputDto = gson.fromJson(json, UserInputDto.class);

                try {
                    userService.save(userInputDto);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
