package com.food.ordering.system.service;

import application.command.CancelPaymentCommand;
import application.command.ProcessPaymentCommand;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.kafka.producer.KafkaProducer;
import com.food.ordering.system.order.service.application.port.output.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${spring.kafka.topic.payment-request-topic}")
    private String paymentRequestTopic;

    private final KafkaProducer<ProcessPaymentCommand> processPaymentMessageProducer;
    private final KafkaProducer<CancelPaymentCommand> cancelPaymentProducer;

    public PaymentServiceImpl(KafkaProducer<ProcessPaymentCommand> processPaymentProducer, KafkaProducer<CancelPaymentCommand> cancelPaymentProducer) {
        this.processPaymentMessageProducer = processPaymentProducer;
        this.cancelPaymentProducer = cancelPaymentProducer;
    }

    @Override
    public void processPayment(OrderId orderId) {
        this.processPaymentMessageProducer.sendMessage(paymentRequestTopic, new ProcessPaymentCommand(orderId));
    }

    @Override
    public void cancelPayment(OrderId orderId) {
        this.cancelPaymentProducer.sendMessage(paymentRequestTopic, new CancelPaymentCommand(orderId));
    }
}
