package com.food.ordering.system.order.data.mapper;

import com.food.ordering.system.order.service.application.entity.Order;

public interface OrderMapper<E> {
    Order toOrder(E entity);
    E toEntity(Order order);
}
