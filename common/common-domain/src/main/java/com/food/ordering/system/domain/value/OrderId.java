package com.food.ordering.system.domain.value;

import java.util.UUID;

public class OrderId extends Identity<UUID> {

    public OrderId(UUID value) {
        super(value);
    }

    public OrderId() {
        super(UUID.randomUUID());
    }

    public UUID getValue() { return value(); }
}
