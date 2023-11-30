package com.food.ordering.system.order.service.application.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateOrderCommand {
    @Size(max = 255)
    private final String description;

    public CreateOrderCommand(String description) {
        this.description = description;
    }

}
