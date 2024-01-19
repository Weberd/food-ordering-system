package com.food.ordering.system.order.service.application.dto;

import com.food.ordering.system.domain.value.OrderId;

public record TrackOrderQuery(OrderId orderId) {
}
