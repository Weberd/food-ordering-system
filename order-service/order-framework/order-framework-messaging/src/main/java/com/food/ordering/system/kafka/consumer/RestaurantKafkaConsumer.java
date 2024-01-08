package com.food.ordering.system.kafka.consumer;

import application.event.InventoryReservedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantKafkaConsumer {
    @KafkaListener(id = "${spring.kafka.topic.restaurant-response-topic}", topics = {
            "${spring.kafka.topic.restaurant-response-topic}",
    })
    public void listenOrderPaid(InventoryReservedEvent event)
    {
        System.out.println(event);
    }
}
