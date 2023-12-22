package com.food.ordering.system.order.data.mapper;

import com.food.ordering.system.order.data.entity.OrderEntity;
import com.food.ordering.system.order.service.application.entity.Order;

public interface OrderMapper {
    Order toOrder(OrderEntity orderEntity);
    OrderEntity toOrderEntity(Order order);
}
