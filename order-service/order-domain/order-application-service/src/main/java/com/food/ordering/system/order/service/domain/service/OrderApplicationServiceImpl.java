package com.food.ordering.system.order.service.domain.service;

import com.food.ordering.system.order.service.domain.dto.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.port.input.OrderApplicationService;

public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        return null;
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return null;
    }
}
