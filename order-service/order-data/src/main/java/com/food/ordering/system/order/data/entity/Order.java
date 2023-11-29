package com.food.ordering.system.order.data.entity;

import com.food.ordering.system.domain.value.OrderId;
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
public final class Order extends com.food.ordering.system.domain.entity.Entity<OrderId> {
    @Id
    private OrderId id;

    @Column
    private String description;

    @Column
    @Enumerated
    private OrderStatus status;

    public Order(OrderId id, String description, OrderStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
}
