package com.food.ordering.system.order.service.application.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public record CancelOrderCommand(UUID orderId) {

}
