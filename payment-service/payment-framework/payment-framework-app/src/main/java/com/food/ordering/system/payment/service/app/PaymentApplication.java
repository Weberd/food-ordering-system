package com.food.ordering.system.payment.service.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
@ComponentScan(basePackages = "com.food.ordering.system")
@EnableKafka
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Value("${spring.kafka.topic.payment-request-topic}")
    private String paymentRequestTopic;

    @Value("${spring.kafka.topic.payment-response-topic}")
    private String paymentResponseTopic;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
            TopicBuilder.name(paymentRequestTopic)
                .partitions(2)
                .replicas(1)
                .build(),
            TopicBuilder.name(paymentResponseTopic)
                    .partitions(2)
                    .replicas(1)
                    .build()
        );
    }
}
