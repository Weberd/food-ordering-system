package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
    Optional<Order> findById(OrderId id);
    Order save(Order order);
}
