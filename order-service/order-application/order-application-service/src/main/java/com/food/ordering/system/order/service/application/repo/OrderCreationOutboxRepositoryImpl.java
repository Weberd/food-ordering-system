package com.food.ordering.system.order.service.application.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.OrderEntity;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;

import java.time.Instant;

public class OrderCreationOutboxRepositoryImpl implements OrderCreationOutboxRepository {
    @Override
    public void save(OrderEntity order) {
    }

    @Override
    public void deleteById(OrderId id) {

    }

    @Override
    public Iterable<OrderEntity> findAllBefore(Instant time) {
        return null;
    }
}
