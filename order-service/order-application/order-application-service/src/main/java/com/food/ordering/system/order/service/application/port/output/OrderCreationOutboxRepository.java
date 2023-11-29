package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.Order;

import java.time.Instant;

public interface OrderCreationOutboxRepository {
    void save(Order order);
    void deleteById(OrderId id);
    Iterable<Order> findAllBefore(Instant time);
}
