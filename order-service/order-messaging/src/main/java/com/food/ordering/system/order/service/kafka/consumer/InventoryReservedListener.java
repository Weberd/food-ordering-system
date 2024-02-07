package com.food.ordering.system.order.service.kafka.consumer;

import com.food.ordering.system.common.messaging.event.InventoryReservedEvent;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.port.input.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InventoryReservedListener {
    private final OrderService orderService;

    @KafkaListener(
        id = "restaurant-inventory-reserved",
        topics = {"${spring.kafka.topic.restaurant-inventory-reservation-response}"}
    )
    public void listenInventoryReserved(InventoryReservedEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.APPROVED);
    }
}
