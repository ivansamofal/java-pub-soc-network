package hello.controllers;

import hello.services.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class KafkaController {
    final private KafkaConsumerService kafkaConsumerService;
    final private KafkaProducerServiceInterface kafkaProducerService;

    public KafkaController(RowService rowService, KafkaConsumerService kafkaConsumerService, KafkaProducerServiceInterface kafkaProducerService) {
        this.kafkaConsumerService = kafkaConsumerService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/cons")
    public String consumer() {
        kafkaConsumerService.consumeUser();

        return "cons";
    }
}