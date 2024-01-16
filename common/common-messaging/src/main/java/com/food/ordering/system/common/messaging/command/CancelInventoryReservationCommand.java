package com.food.ordering.system.common.messaging.command;

import com.food.ordering.system.domain.value.OrderId;

public record CancelInventoryReservationCommand(OrderId orderId) {
}
