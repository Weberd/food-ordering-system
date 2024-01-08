package com.food.ordering.system.kafka.consumer;

import com.food.ordering.system.kafka.event.OrderPaidEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantKafkaConsumer {
    @KafkaListener(id = "${spring.kafka.topic.restaurant-response-topic}", topics = {
            "${spring.kafka.topic.restaurant-response-topic}",
    })
    public void listen(OrderPaidEvent in)
    {
        System.out.println(in);
    }
}
