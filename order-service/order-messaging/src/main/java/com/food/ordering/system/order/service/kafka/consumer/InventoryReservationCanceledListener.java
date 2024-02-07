package com.food.ordering.system.order.service.kafka.consumer;

import com.food.ordering.system.common.messaging.event.InventoryReservationCanceledEvent;
import com.food.ordering.system.common.messaging.event.InventoryReservedEvent;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.application.port.input.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InventoryReservationCanceledListener {
    private final OrderService orderService;

    @KafkaListener(
        id = "restaurant-inventory-reservation-canceled",
        topics = {"${spring.kafka.topic.restaurant-inventory-reservation-cancel-response}"}
    )
    public void listenInventoryReservationCanceled(InventoryReservationCanceledEvent event)
    {
        var trackOrderResponse = orderService.trackOrder(new TrackOrderQuery(event.orderId()));

        if (trackOrderResponse.status() == OrderStatus.CANCELING) {
            orderService.updateStatus(event.orderId(), OrderStatus.CANCELED);
        }
    }
}
