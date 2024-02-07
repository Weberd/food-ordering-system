package com.food.ordering.system.payment.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelPaymentCommand;
import com.food.ordering.system.common.messaging.command.ProcessPaymentCommand;
import com.food.ordering.system.common.messaging.event.OrderPaidEvent;
import com.food.ordering.system.common.messaging.event.OrderPaymentCanceledEvent;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcessPaymentListener {
    @Value("${spring.kafka.topic.payment-process-response}")
    private String paymentResponseTopic;
    private final KafkaProducer<OrderPaidEvent> orderPaidEventProducer;

    @KafkaListener(
        id = "payment-process",
        topics = {"${spring.kafka.topic.payment-process-request}"}
    )
    public void processPayment(ProcessPaymentCommand command) {
        orderPaidEventProducer.sendMessage(paymentResponseTopic, new OrderPaidEvent(command.orderId()));
    }
}
