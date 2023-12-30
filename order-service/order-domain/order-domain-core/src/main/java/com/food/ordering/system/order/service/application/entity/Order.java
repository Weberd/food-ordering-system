package com.food.ordering.system.order.service.application.entity;

import com.food.ordering.system.domain.entity.Entity;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Order extends Entity<OrderId> {
    private String description;
    private OrderStatus status;

    public Order(OrderId orderId, String orderDescription, OrderStatus orderStatus) {
        id = orderId;
        description = orderDescription;
        status = orderStatus;
    }
}
