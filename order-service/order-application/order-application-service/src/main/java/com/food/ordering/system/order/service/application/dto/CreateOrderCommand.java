package com.food.ordering.system.order.service.application.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateOrderCommand {
    @Size(max = 5)
    private String description;
}
