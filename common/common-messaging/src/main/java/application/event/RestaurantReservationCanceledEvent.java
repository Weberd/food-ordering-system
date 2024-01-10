package application.event;

import com.food.ordering.system.domain.value.OrderId;

public record RestaurantReservationCanceledEvent(OrderId orderId) {
}
