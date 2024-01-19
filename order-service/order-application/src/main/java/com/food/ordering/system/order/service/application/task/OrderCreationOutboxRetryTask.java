package com.food.ordering.system.order.service.application.task;

import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public record OrderCreationOutboxRetryTask(OrderCreationOutboxRepository outboxRepository) {
    @Scheduled(fixedDelay = 10000)
    void retry() {
        Iterable<Order> orderList = outboxRepository.findAllBefore(Instant.now().minusSeconds(60));
        for (Order order : orderList) {
            try {
                outboxRepository.deleteById(order.id());
            } catch (Exception e) {
                log.error("Order creation task failed failed: " + e.getMessage(), order.id());
            }
        }
    }
}
