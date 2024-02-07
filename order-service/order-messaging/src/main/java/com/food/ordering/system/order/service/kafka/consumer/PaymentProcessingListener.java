package com.food.ordering.system.order.service.kafka.consumer;

import com.food.ordering.system.common.messaging.event.OrderPaidEvent;
import com.food.ordering.system.common.messaging.event.OrderPaymentCanceledEvent;
import com.food.ordering.system.common.messaging.event.OrderPaymentFailedEvent;
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
public class PaymentProcessingListener {
    private final OrderService orderService;

    @KafkaListener(
        id = "payment-process",
        topics = {"${spring.kafka.topic.payment-process-response}"}
    )
    public void listenProcessPayment(OrderPaidEvent event) {
        orderService.updateStatus(event.orderId(), OrderStatus.PAID);
    }
}
