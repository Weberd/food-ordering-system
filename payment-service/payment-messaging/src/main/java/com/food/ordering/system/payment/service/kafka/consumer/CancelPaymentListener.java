package com.food.ordering.system.payment.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelPaymentCommand;
import com.food.ordering.system.common.messaging.event.OrderPaymentCanceledEvent;
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
public class CancelPaymentListener {
    @Value("${spring.kafka.topic.payment-cancel-response}")
    private String paymentResponseTopic;
    private final KafkaProducer<OrderPaymentCanceledEvent> paidOrderCanceledEventProducer;

    @KafkaListener(
        id = "payment-cancel",
        topics = {"${spring.kafka.topic.payment-cancel-response}"}
    )
    public void cancelPayment(@Payload CancelPaymentCommand command) {
        paidOrderCanceledEventProducer.sendMessage(paymentResponseTopic, new OrderPaymentCanceledEvent(command.orderId()));
    }
}
