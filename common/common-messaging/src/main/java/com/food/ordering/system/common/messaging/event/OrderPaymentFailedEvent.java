package com.food.ordering.system.common.messaging.event;

import com.food.ordering.system.domain.value.OrderId;

public record OrderPaymentFailedEvent(OrderId orderId, String message) {
}
