package com.food.ordering.system.order.service.application.dto;

import lombok.Getter;

@Getter
public class CreateOrderCommand {
    private final String description;

    public CreateOrderCommand(String description) {
        this.description = description;
    }

}
