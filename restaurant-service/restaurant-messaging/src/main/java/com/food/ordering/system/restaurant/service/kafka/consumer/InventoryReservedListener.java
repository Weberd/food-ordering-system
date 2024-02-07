package com.food.ordering.system.restaurant.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.ReserveInventoryCommand;
import com.food.ordering.system.common.messaging.event.InventoryReservedEvent;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InventoryReservedListener {
    @Value("${spring.kafka.topic.restaurant-inventory-reservation-response}")
    private String inventoryReservedTopic;
    private final KafkaProducer<InventoryReservedEvent> inventoryReservedEventProducer;

    @KafkaListener(
        id = "restaurant-inventory-reserved",
        topics = {"${spring.kafka.topic.restaurant-inventory-reservation-request}"}
    )
    public void inventoryReserved(@Payload ReserveInventoryCommand command) {
        inventoryReservedEventProducer.sendMessage(inventoryReservedTopic, new InventoryReservedEvent(command.orderId()));
    }
}
