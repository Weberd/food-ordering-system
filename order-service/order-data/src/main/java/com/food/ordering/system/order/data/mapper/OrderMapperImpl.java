package com.food.ordering.system.order.data.mapper;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.OrderEntity;
import com.food.ordering.system.order.service.application.entity.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class OrderMapperImpl implements OrderMapper {
    public Order toOrder(OrderEntity orderEntity) {
        return new Order(
                new OrderId(orderEntity.getId()),
                orderEntity.getDescription(),
                orderEntity.getStatus()
        );
    }

    @Override
    public OrderEntity toOrderEntity(Order order) {
        return new OrderEntity(order.id().value(), order.getDescription(), order.getStatus());
    }
}
