package com.food.ordering.system.order.service.application.exception;

import com.food.ordering.system.domain.value.OrderId;

public class CancelOrderException extends RuntimeException {
    private final OrderId orderId;

    public CancelOrderException(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
