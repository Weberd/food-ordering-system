package com.food.ordering.system.order.service.application.port.input;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.dto.*;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderCommand command);
    TrackOrderResponse trackOrder(TrackOrderQuery query);
    CancelOrderResponse cancelOrder(CancelOrderCommand command);
    void updateStatus(OrderId orderId, OrderStatus status);
}
