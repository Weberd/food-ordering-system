package com.food.ordering.system.order.service.domain.port.input;

import com.food.ordering.system.order.service.domain.dto.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.TrackOrderResponse;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(CreateOrderCommand command);

    TrackOrderResponse trackOrder(TrackOrderQuery query);
}
