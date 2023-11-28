package com.food.ordering.system.order.service.application.dto;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;

public record TrackOrderResponse(OrderId orderId, OrderStatus status) {
}
