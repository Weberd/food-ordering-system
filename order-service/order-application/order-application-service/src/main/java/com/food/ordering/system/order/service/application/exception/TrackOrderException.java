package com.food.ordering.system.order.service.application.exception;

import com.food.ordering.system.domain.value.OrderId;

public class TrackOrderException extends RuntimeException {
    public TrackOrderException(OrderId orderId) {
    }
}
