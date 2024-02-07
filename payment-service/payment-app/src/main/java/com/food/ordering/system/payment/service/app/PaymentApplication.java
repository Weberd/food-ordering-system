package com.food.ordering.system.payment.service.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
@ComponentScan(basePackages = "com.food.ordering.system")
@EnableKafka
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Value("${spring.kafka.topic.payment-process-request}")
    private String paymentProcessRequestTopic;

    @Value("${spring.kafka.topic.payment-process-response}")
    private String paymentProcessResponseTopic;

    @Value("${spring.kafka.topic.payment-cancel-request}")
    private String paymentCancelRequestTopic;

    @Value("${spring.kafka.topic.payment-cancel-response}")
    private String paymentCancelResponseTopic;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
            TopicBuilder.name(paymentProcessRequestTopic)
                .partitions(2)
                .replicas(1)
                .build(),
            TopicBuilder.name(paymentProcessResponseTopic)
                .partitions(2)
                .replicas(1)
                .build(),
            TopicBuilder.name(paymentCancelRequestTopic)
                .partitions(2)
                .replicas(1)
                .build(),
            TopicBuilder.name(paymentCancelResponseTopic)
                .partitions(2)
                .replicas(1)
                .build()
        );
    }
}
