package com.food.ordering.system.order.service.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
@ComponentScan(basePackages = "com.food.ordering.system.*")
@EnableJpaRepositories(basePackages = "com.food.ordering.system.order.data.repo.*")
@EntityScan("com.food.ordering.system.order.data.entity.*")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Value("${spring.kafka.topic.payment-request-topic}")
    private String paymentRequestTopic;

    @Value("${spring.kafka.topic.payment-response-topic}")
    private String paymentResponseTopic;

    @Value("${spring.kafka.topic.restaurant-request-topic}")
    private String restaurantRequestTopic;

    @Value("${spring.kafka.topic.restaurant-response-topic}")
    private String restaurantResponseTopic;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
            TopicBuilder.name(paymentRequestTopic)
                .partitions(2)
                .replicas(2)
                .build(),
            TopicBuilder.name(paymentResponseTopic)
                    .partitions(2)
                    .replicas(2)
                    .build(),
            TopicBuilder.name(restaurantRequestTopic)
                    .partitions(2)
                    .replicas(2)
                    .build(),
            TopicBuilder.name(restaurantResponseTopic)
                    .partitions(2)
                    .replicas(2)
                    .build()
        );
    }

    @KafkaListener(id = "payment-response", topics = {
            "${spring.kafka.topic.payment-response-topic}",
        }
    )
    public void listenPaymentResponse(String in) {
        System.out.println(in);
    }

    @KafkaListener(id = "restaurant-response", topics = {
            "${spring.kafka.topic.restaurant-response-topic}",
        }
    )
    public void listenRestaurantResponse(String in) {
        System.out.println(in);
    }
}
