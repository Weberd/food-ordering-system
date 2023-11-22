package com.food.ordering.system.order.service.application.service;

import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.dto.*;
import com.food.ordering.system.order.service.application.port.input.OrderApplicationService;

public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        return new CreateOrderResponse();
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return new TrackOrderResponse(query.orderId(), OrderStatus.PENDING);
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderCommand command) {
        return new CancelOrderResponse(command.getOrderId());
    }
}
