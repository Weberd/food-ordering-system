package com.food.ordering.system.domain.value;

import com.food.ordering.system.domain.value.Identity;

import java.util.UUID;

public class OrderId extends Identity<UUID> {

    public OrderId(UUID value) {
        super(value);
    }
}
