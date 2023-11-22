package com.food.ordering.system.order.service.application.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CancelOrderCommand {
    private final UUID orderId;

    public CancelOrderCommand(UUID orderId) {
        this.orderId = orderId;
    }

}
