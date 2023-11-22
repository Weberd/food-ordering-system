package com.food.ordering.system.order.service.application.dto;

import com.food.ordering.system.domain.value.OrderStatus;

import java.util.UUID;

public record TrackOrderResponse(UUID orderId, OrderStatus status) {
}
