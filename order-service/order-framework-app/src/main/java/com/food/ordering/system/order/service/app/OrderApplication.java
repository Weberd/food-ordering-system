package com.food.ordering.system.order.service.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
@ComponentScan(basePackages = "com.food.ordering.system")
@EntityScan("com.food.ordering.system.order.service.data.entity")
@EnableJpaRepositories(basePackages = "com.food.ordering.system.order.service.data.repo")
@EnableKafka
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-request}")
    private String restaurantInventoryReservationRequest;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-response}")
    private String restaurantInventoryReservationResponse;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-cancel-request}")
    private String restaurantInventoryReservationCancelRequest;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-cancel-response}")
    private String restaurantInventoryReservationCancelResponse;

    @Value("${spring.kafka.topic.payment-process-request}")
    private String paymentProcessRequest;

    @Value("${spring.kafka.topic.payment-process-response}")
    private String paymentProcessResponse;

    @Value("${spring.kafka.topic.payment-cancel-request}")
    private String paymentCancelRequest;

    @Value("${spring.kafka.topic.payment-cancel-response}")
    private String paymentCancelResponse;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(restaurantInventoryReservationRequest)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(restaurantInventoryReservationResponse)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(restaurantInventoryReservationCancelRequest)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(restaurantInventoryReservationCancelResponse)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(paymentProcessRequest)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(paymentProcessResponse)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(paymentCancelRequest)
                        .partitions(2)
                        .replicas(1)
                        .build(),
                TopicBuilder.name(paymentCancelResponse)
                        .partitions(2)
                        .replicas(1)
                        .build()
        );
    }
}
