package com.food.ordering.system.order.service.service;

import com.food.ordering.system.common.messaging.command.CancelPaymentCommand;
import com.food.ordering.system.common.messaging.command.ProcessPaymentCommand;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import com.food.ordering.system.order.service.application.port.output.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${spring.kafka.topic.payment-process-request}")
    private String paymentProcessRequestTopic;

    @Value("${spring.kafka.topic.payment-cancel-request}")
    private String paymentCancelRequestTopic;

    private final KafkaProducer<ProcessPaymentCommand> processPaymentMessageProducer;
    private final KafkaProducer<CancelPaymentCommand> cancelPaymentProducer;

    public PaymentServiceImpl(KafkaProducer<ProcessPaymentCommand> processPaymentProducer, KafkaProducer<CancelPaymentCommand> cancelPaymentProducer) {
        this.processPaymentMessageProducer = processPaymentProducer;
        this.cancelPaymentProducer = cancelPaymentProducer;
    }

    @Override
    public void processPayment(OrderId orderId) {
        this.processPaymentMessageProducer.sendMessage(paymentProcessRequestTopic, new ProcessPaymentCommand(orderId));
    }

    @Override
    public void cancelPayment(OrderId orderId) {
        this.cancelPaymentProducer.sendMessage(paymentCancelRequestTopic, new CancelPaymentCommand(orderId));
    }
}
