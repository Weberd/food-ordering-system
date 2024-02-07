package com.food.ordering.system.restaurant.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelInventoryReservationCommand;
import com.food.ordering.system.common.messaging.event.InventoryReservationCanceledEvent;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CancelInventoryReservationListener {
    @Value("${spring.kafka.topic.restaurant-inventory-reservation-cancel-response}")
    private String cancelInventoryReservationTopic;
    private final KafkaProducer<InventoryReservationCanceledEvent> inventoryReservationCanceledEventProducer;

    @KafkaListener(
        id = "restaurant-cancel-inventory-reservation",
        topics = {"${spring.kafka.topic.restaurant-inventory-reservation-cancel-request}"}
    )
    public void cancelInventoryReservation(CancelInventoryReservationCommand command) {
        inventoryReservationCanceledEventProducer.sendMessage(cancelInventoryReservationTopic, new InventoryReservationCanceledEvent(command.orderId()));
    }
}
