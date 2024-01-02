package com.food.ordering.system.order.service.application.dto;

import com.food.ordering.system.domain.value.OrderId;

import java.util.UUID;

public record CreateOrderResponse (OrderId orderId) {

}
