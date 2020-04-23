//package hello.configuration;
//
//import hello.dtos.RowDto;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.LongSerializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.serializer.JsonSerializer;
////import org.apache.kafka.connect.json.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
//    @Value(value = "${kafka.server}")
////    final static private String bootstrapAddress = "kafka1:9092";
//    private String bootstrapAddress;
//
////    @Bean
////    public ProducerFactory<String, String> producerFactory() {
////        Map<String, Object> configProps = new HashMap<>();
////        configProps.put(
////                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
////                bootstrapAddress);
////        configProps.put(
////                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
////                StringSerializer.class);
////        configProps.put(
////                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
////                StringSerializer.class);
////        return new DefaultKafkaProducerFactory<>(configProps);
////    }
////
////    @Bean
////    public KafkaTemplate<String, String> kafkaTemplate() {
////        return new KafkaTemplate<>(producerFactory());
////    }
//
//
//    @Value("${kafka.producer.id}")
//    private String kafkaProducerId;
//
////    @Bean
////    public Map<String, Object> producerConfigs() {
////        Map<String, Object> props = new HashMap<>();
////        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
////        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
//////        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
////        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
////        return props;
////    }
////
////    @Bean
////    public ProducerFactory<Long, RowDto> producerStarshipFactory() {
////        return new DefaultKafkaProducerFactory<>(producerConfigs());
////    }
////
////    @Bean
////    public KafkaTemplate<Long, RowDto> kafkaTemplate() {
////        KafkaTemplate<Long, RowDto> template = new KafkaTemplate<>(producerStarshipFactory());
////        template.setMessageConverter(new StringJsonMessageConverter());
////        return template;
////    }
//}
