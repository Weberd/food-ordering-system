package com.food.ordering.system.payment.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelPaymentCommand;
import com.food.ordering.system.common.messaging.command.ProcessPaymentCommand;
import com.food.ordering.system.common.messaging.event.OrderPaidEvent;
import com.food.ordering.system.common.messaging.event.OrderPaymentCanceledEvent;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderKafkaConsumer {
    @Value("${spring.kafka.topic.payment-response-topic}")
    private String paymentResponseTopic;

    private final KafkaProducer<OrderPaidEvent> orderPaidEventProducer;
    private final KafkaProducer<OrderPaymentCanceledEvent> paidOrderCanceledEventProducer;

    @KafkaListener(id = "process-payment", topics = {
        "${spring.kafka.topic.payment-request-topic}"
    })
    public void processPayment(ProcessPaymentCommand command) {
        orderPaidEventProducer.sendMessage(paymentResponseTopic, new OrderPaidEvent(command.orderId()));
    }

    @KafkaListener(id = "cancel-payment", topics = {
        "${spring.kafka.topic.payment-request-topic}"
    })
    public void cancelPayment(CancelPaymentCommand command) {
        paidOrderCanceledEventProducer.sendMessage(paymentResponseTopic, new OrderPaymentCanceledEvent(command.orderId()));
    }
}
