package hello.services;

public interface KafkaProducerServiceInterface {
    /**
     *
     * @param topicName String
     * @param json String
     */
    void produce(String topicName, String json);

    void testProducer();
}
