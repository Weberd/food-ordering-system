package com.food.ordering.system.order.data.entity;

import com.food.ordering.system.domain.value.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public final class OrderEntity {
    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    public OrderEntity(String id, String description, OrderStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @Column
    private String description;

    @Column
    @Enumerated
    private OrderStatus status;
}
