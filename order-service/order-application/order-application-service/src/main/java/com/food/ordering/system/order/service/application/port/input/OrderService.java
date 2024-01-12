package com.food.ordering.system.order.service.application.port.input;

import com.food.ordering.system.order.service.application.dto.*;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderCommand command);

    TrackOrderResponse trackOrder(TrackOrderQuery query);

    CancelOrderResponse cancelOrder(CancelOrderCommand command);
}
