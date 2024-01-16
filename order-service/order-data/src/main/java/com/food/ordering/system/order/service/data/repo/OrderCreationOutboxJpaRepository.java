package com.food.ordering.system.order.service.data.repo;

import com.food.ordering.system.order.service.data.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface OrderCreationOutboxJpaRepository extends JpaRepository<OrderOutboxEntity, UUID> {
    @Query("select o from OrderOutboxEntity o where o.created < :time")
    Iterable<OrderOutboxEntity> findAllBefore(Instant time);
}
