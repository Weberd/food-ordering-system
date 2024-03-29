package com.food.ordering.system.order.service.data.mapper;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.data.entity.OrderOutboxEntity;
import com.food.ordering.system.order.service.application.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderOutboxEntityImpl implements OrderMapper<OrderOutboxEntity> {
    @Override
    public Order toOrder(OrderOutboxEntity entity) {
        return new Order(new OrderId(entity.getId()), entity.getDescription(), entity.getStatus());
    }

    @Override
    public OrderOutboxEntity toEntity(Order order) {
        return new OrderOutboxEntity(order.id().value(), order.getDescription(), order.getStatus());
    }
}
