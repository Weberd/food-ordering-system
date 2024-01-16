package com.food.ordering.system.order.service.kafka.consumer;

import com.food.ordering.system.common.messaging.event.OrderPaidEvent;
import com.food.ordering.system.common.messaging.event.OrderPaymentFailedEvent;
import com.food.ordering.system.common.messaging.event.PaidOrderCanceledEvent;
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
@KafkaListener(id = "${spring.kafka.topic.payment-response-topic}", topics = {
        "${spring.kafka.topic.payment-response-topic}",
})
public class PaymentKafkaConsumer {
    private final OrderService orderService;

    @KafkaHandler
    public void listenOrderPaid(OrderPaidEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.PAID);
    }

    @KafkaHandler
    public void listenOrderPaymentCanceled(PaidOrderCanceledEvent event) {
        var trackOrderResponse = orderService.trackOrder(new TrackOrderQuery(event.orderId()));

        if (trackOrderResponse.status() == OrderStatus.CANCELING) {
            orderService.updateStatus(event.orderId(), OrderStatus.CANCELED);
        }
    }

    @KafkaHandler
    public void listenOrderPaymentFailed(OrderPaymentFailedEvent event)
    {
        log.error("Order payment " + event.orderId() + " failed. " + event.message());
    }
}
