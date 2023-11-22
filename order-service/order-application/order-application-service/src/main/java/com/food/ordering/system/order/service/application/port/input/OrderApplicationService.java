package com.food.ordering.system.order.service.application.port.input;

import com.food.ordering.system.order.service.application.dto.*;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(CreateOrderCommand command);

    TrackOrderResponse trackOrder(TrackOrderQuery query);
}
