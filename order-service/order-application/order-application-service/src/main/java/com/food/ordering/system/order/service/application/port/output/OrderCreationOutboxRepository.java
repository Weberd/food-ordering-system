package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface OrderCreationOutboxRepository extends JpaRepository<Order, OrderId> {
    Order save(Order order);
    void deleteById(OrderId id);
    @Query("select o from Order o where s.created < :time")
    Iterable<Order> findAllBefore(Instant time);
}
