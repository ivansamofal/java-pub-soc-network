package hello.services;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaProducerService implements KafkaProducerServiceInterface {
    public void produce(String topicName, String json) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka1:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("TRY TO SEND IN KAFKA22!!!!!!!");

        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(topicName, "1", json));

        producer.close();
    }

    public void testProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafka1:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("TRY TO SEND IN KAFKA!!!!!!!");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 5; i++)
            producer.send(new ProducerRecord<>("test", Integer.toString(i), Integer.toString(i)));

        producer.close();
    }
}
