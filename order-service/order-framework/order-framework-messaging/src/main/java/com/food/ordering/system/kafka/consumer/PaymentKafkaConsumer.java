package com.food.ordering.system.kafka.consumer;

import application.event.OrderPaidEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer {
    @KafkaListener(id = "${spring.kafka.topic.payment-response-topic}", topics = {
            "${spring.kafka.topic.payment-response-topic}",
    })
    public void listenOrderPaid(OrderPaidEvent event)
    {
        System.out.println(event);
    }
}
