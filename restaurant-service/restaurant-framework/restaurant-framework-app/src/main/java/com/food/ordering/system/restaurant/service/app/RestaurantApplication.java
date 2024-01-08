package com.food.ordering.system.restaurant.service.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
@ComponentScan(basePackages = "com.food.ordering.system")
public class RestaurantApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    @Value("${spring.kafka.topic.restaurant-request-topic}")
    private String restaurantRequestTopic;

    @Value("${spring.kafka.topic.restaurant-response-topic}")
    private String restaurantResponseTopic;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
            TopicBuilder.name(restaurantRequestTopic)
                    .partitions(2)
                    .replicas(1)
                    .build(),
            TopicBuilder.name(restaurantResponseTopic)
                    .partitions(2)
                    .replicas(1)
                    .build()
        );
    }

    @KafkaListener(id = "id", topics = {
            "${spring.kafka.topic.restaurant-request-topic}",
        }
    )
    public void listenRestaurantRequest(String in) {
        System.out.println(in);
    }
}
