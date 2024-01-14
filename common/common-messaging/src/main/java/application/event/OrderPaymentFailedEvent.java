package application.event;

import com.food.ordering.system.domain.value.OrderId;

public record OrderPaymentFailedEvent(OrderId orderId, String message) {
}
