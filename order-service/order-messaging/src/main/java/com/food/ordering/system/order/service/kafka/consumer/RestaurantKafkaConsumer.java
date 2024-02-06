package com.food.ordering.system.order.service.kafka.consumer;

import com.food.ordering.system.common.messaging.event.InventoryReservationCanceledEvent;
import com.food.ordering.system.common.messaging.event.InventoryReservationFailedEvent;
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
@KafkaListener(id = "restaurant-consumer", topics = {
        "${spring.kafka.topic.restaurant-response-topic}",
})
public class RestaurantKafkaConsumer {
    private final OrderService orderService;

    @KafkaHandler
    public void listenInventoryReserved(InventoryReservedEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.PAID);
    }

    @KafkaHandler
    public void listenInventoryReservationCanceled(InventoryReservationCanceledEvent event)
    {
        var trackOrderResponse = orderService.trackOrder(new TrackOrderQuery(event.orderId()));

        if (trackOrderResponse.status() == OrderStatus.CANCELING) {
            orderService.updateStatus(event.orderId(), OrderStatus.CANCELED);
        }
    }

    @KafkaHandler
    public void listenInventoryReservationFailed(InventoryReservationFailedEvent event)
    {
        log.error("Inventory reservation for Order " + event.orderId() + " failed. " + event.message());
    }
}
