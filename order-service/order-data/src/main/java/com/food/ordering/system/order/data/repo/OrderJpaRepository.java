package com.food.ordering.system.order.data.repo;

import com.food.ordering.system.order.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    @Override
    Optional<OrderEntity> findById(UUID uuid);
}
