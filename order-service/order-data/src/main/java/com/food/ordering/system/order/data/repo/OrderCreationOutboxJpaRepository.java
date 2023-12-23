package com.food.ordering.system.order.data.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface OrderCreationOutboxJpaRepository extends JpaRepository<Order, OrderId> {
    void deleteById(OrderId id);
    @Query("select o from Order o where s.created < :time")
    Iterable<Order> findAllBefore(Instant time);
}
