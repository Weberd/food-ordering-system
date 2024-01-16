package com.food.ordering.system.payment.service.kafka.consumer;

import com.food.ordering.system.common.messaging.command.CancelPaymentCommand;
import com.food.ordering.system.common.messaging.command.ProcessPaymentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(id = "${spring.kafka.topic.payment-request-topic}", topics = {
        "${spring.kafka.topic.payment-request-topic}",
})
public class OrderKafkaConsumer {
    @KafkaHandler
    public void processPayment(ProcessPaymentCommand command) {

    }

    @KafkaHandler
    public void cancelPayment(CancelPaymentCommand command) {

    }
}
