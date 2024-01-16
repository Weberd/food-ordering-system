package com.food.ordering.system.common.messaging.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, T> template) {
        kafkaTemplate = template;
    }

    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message);
    }
}
