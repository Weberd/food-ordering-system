package com.food.ordering.system.restaurant.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelInventoryReservationCommand;
import com.food.ordering.system.common.messaging.command.ReserveInventoryCommand;
import com.food.ordering.system.common.messaging.event.InventoryReservationCanceledEvent;
import com.food.ordering.system.common.messaging.event.InventoryReservedEvent;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(id = "${spring.kafka.topic.restaurant-request-topic}", topics = {
    "${spring.kafka.topic.restaurant-request-topic}",
})
public class OrderKafkaConsumer {
    @Value("${spring.kafka.topic.restaurant-response-topic}")
    private final String restaurantResponseTopic;

    private final KafkaProducer<InventoryReservedEvent> inventoryReservedEventProducer;
    private final KafkaProducer<InventoryReservationCanceledEvent> inventoryReservationCanceledEventProducer;

    @KafkaHandler
    public void inventoryReserved(ReserveInventoryCommand command) {
        inventoryReservedEventProducer.sendMessage(restaurantResponseTopic, new InventoryReservedEvent(command.orderId()));
    }

    @KafkaHandler
    public void cancelInventoryReservation(CancelInventoryReservationCommand command) {
        inventoryReservationCanceledEventProducer.sendMessage(restaurantResponseTopic, new InventoryReservationCanceledEvent(command.orderId()));
    }
}
