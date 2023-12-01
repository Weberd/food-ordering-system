package com.food.ordering.system.order.service.application.task;

import com.food.ordering.system.order.data.entity.OrderEntity;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public record OrderCreationOutboxRetryTask(OrderCreationOutboxRepository outboxRepository) {
    @Scheduled(fixedDelay = 10000)
    void retry() {
        Iterable<OrderEntity> orderList = outboxRepository.findAllBefore(Instant.now().minusSeconds(60));
        for (OrderEntity order : orderList) {
            try {
                // TODO: send messages
                outboxRepository.deleteById(order.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
