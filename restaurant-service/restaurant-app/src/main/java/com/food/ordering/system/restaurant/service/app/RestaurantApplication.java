package com.food.ordering.system.restaurant.service.app;

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
public class RestaurantApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-request}")
    private String restaurantInventoryReservationRequest;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-response}")
    private String restaurantInventoryReservationResponse;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-cancel-request}")
    private String restaurantInventoryReservationCancelRequest;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-cancel-response}")
    private String restaurantInventoryReservationCancelResponse;

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
                        .build()
        );
    }
}
