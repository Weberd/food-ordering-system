package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepository {
    void save(OrderEntity order);
    Optional<OrderEntity> findById(OrderId id);
}
