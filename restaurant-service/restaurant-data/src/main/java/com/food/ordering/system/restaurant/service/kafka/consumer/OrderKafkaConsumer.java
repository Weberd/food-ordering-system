package com.food.ordering.system.restaurant.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelReservationCommand;
import com.food.ordering.system.common.messaging.command.ReserveInventoryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(id = "${spring.kafka.topic.restaurant-request-topic}", topics = {
        "${spring.kafka.topic.restaurant-request-topic}",
})
public class OrderKafkaConsumer {
    @KafkaHandler
    public void inventoryReserved(ReserveInventoryCommand command) {

    }

    @KafkaHandler
    public void cancelInventoryReservation(CancelReservationCommand command) {

    }
}
