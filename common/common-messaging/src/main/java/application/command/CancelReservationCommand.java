package application.command;

import com.food.ordering.system.domain.value.OrderId;

public record CancelReservationCommand(OrderId orderId) {
}
