package com.food.ordering.system.order.service.application.task;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;
import com.food.ordering.system.order.service.application.saga.CreateOrderSaga;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCreationOutboxRetryTask
{
    private final OrderCreationOutboxRepository outboxRepository;
    private final CreateOrderSaga createOrderSaga;

    @Scheduled(fixedDelay = 10000)
    void retry() {
        Iterable<Order> orderList = outboxRepository.findAllBefore(Instant.now().minusSeconds(60));
        for (Order order : orderList) {
            try {
                createOrderSaga.placeOrder(order);
            } catch (Exception e) {
                log.error("Order creation retry task failed: " + e.getMessage(), order.id());
            }
        }
    }
}
