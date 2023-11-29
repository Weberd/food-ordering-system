package com.food.ordering.system.order.service.application.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;

import java.time.Instant;

public class OrderCreationOutboxRepositoryImpl implements OrderCreationOutboxRepository {
    @Override
    public void save(Order order) {
    }

    @Override
    public void deleteById(OrderId id) {

    }

    @Override
    public Iterable<Order> findAllBefore(Instant time) {
        return null;
    }
}
