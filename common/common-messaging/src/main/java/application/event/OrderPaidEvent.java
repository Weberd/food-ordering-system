package application.event;

import com.food.ordering.system.domain.value.OrderId;

public record OrderPaidEvent(OrderId orderId) {
}
