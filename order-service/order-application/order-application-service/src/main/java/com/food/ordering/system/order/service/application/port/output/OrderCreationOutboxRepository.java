package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface OrderCreationOutboxRepository extends JpaRepository<Order, OrderId> {
    Order save(Order order);
    void deleteById(OrderId id);
    Iterable<Order> findAllBefore(Instant time);
}
