package com.food.ordering.system.order.data.entity;

import com.food.ordering.system.domain.value.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public final class OrderEntity {
    @Id
    @Column
    private UUID id;

    @Column
    private String description;

    @Column
    @Enumerated
    private OrderStatus status;

    public OrderEntity(UUID id, String description, OrderStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
}
