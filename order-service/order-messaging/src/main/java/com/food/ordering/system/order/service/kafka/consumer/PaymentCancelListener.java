package com.food.ordering.system.order.service.kafka.consumer;

import com.food.ordering.system.common.messaging.event.OrderPaymentCanceledEvent;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.application.port.input.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentCancelListener {
    private final OrderService orderService;

    @KafkaListener(
        id = "payment-cancel",
        topics = {"${spring.kafka.topic.payment-cancel-response}"}
    )
    public void listenOrderPaymentCanceled(OrderPaymentCanceledEvent event) {
        var trackOrderResponse = orderService.trackOrder(new TrackOrderQuery(event.orderId()));

        if (trackOrderResponse.status() == OrderStatus.CANCELING) {
            orderService.updateStatus(event.orderId(), OrderStatus.CANCELED);
        }
    }
}
