package hello.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaService {

    @Value(value = "${kafka.server}")
    private static String kafkaServer;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private static String kafkaGroupId;
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private static String TOPIC_NAME = "baeldung";
//
//    public void sendSomeMessage(String msg) {
//        kafkaTemplate.send(TOPIC_NAME, msg);//todo
//    }
//
//    public void sendMessage(String message) {
//        ListenableFuture<SendResult<String, String>> future =
//                kafkaTemplate.send(TOPIC_NAME, message);
//
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                System.out.println("Sent message=[" + message +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            }
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("Unable to send message=["
//                        + message + "] due to : " + ex.getMessage());
//            }
//        });
//    }
//
//    @KafkaListener(topics = "topicName", groupId = "foo")
//    public void listen(String message) {
//        System.out.println("Received Messasge in group foo: " + message);
//    }

    public void testProducer() {
        System.out.println("kafkaServer " + kafkaServer);
        System.out.println("kafkaGroupId " + kafkaGroupId);
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

    public void testConsumer() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "kafka1:9092");
        props.setProperty("group.id", "consumer1");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    public void testStream() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        builder.<String, String>stream("test").mapValues(value -> {
            System.out.println("value " + value);
            return String.valueOf(value.length());
        }).to("test-output");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
