package com.food.ordering.system.order.service.application.port.input;

import com.food.ordering.system.order.service.application.dto.CreateOrderCommand;
import com.food.ordering.system.order.service.application.dto.CreateOrderResponse;
import com.food.ordering.system.order.service.application.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.application.dto.TrackOrderResponse;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(CreateOrderCommand command);

    TrackOrderResponse trackOrder(TrackOrderQuery query);
}
