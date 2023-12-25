package com.food.ordering.system.order.data.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.OrderEntity;
import com.food.ordering.system.order.data.entity.OrderOutboxEntity;
import com.food.ordering.system.order.service.application.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface OrderCreationOutboxJpaRepository extends JpaRepository<OrderOutboxEntity, UUID> {
    @Query("select o from OrderOutboxEntity o where s.created < :time")
    Iterable<OrderOutboxEntity> findAllBefore(Instant time);
}
