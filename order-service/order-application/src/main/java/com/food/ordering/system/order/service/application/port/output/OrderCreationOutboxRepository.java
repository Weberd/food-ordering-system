package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;

import java.time.Instant;
import java.util.Optional;

public interface OrderCreationOutboxRepository {
    void deleteById(OrderId id);
    Iterable<Order> findAllBefore(Instant time);
    Optional<Order> findById(OrderId id);
    void save(Order order);
}
