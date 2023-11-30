package com.food.ordering.system.order.data.entity;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public final class Order extends com.food.ordering.system.domain.entity.Entity<OrderId> {
    @Id
    private OrderId id;

    public Order(OrderId id, String description, OrderStatus status) {
        super(id);
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
