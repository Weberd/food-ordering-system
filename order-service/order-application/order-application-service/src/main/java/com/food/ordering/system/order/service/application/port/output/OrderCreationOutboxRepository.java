package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.OrderEntity;

import java.time.Instant;

public interface OrderCreationOutboxRepository {
    void save(OrderEntity order);
    void deleteById(OrderId id);
    Iterable<OrderEntity> findAllBefore(Instant time);
}
