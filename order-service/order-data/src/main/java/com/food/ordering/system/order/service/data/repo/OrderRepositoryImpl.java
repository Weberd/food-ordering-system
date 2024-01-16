package com.food.ordering.system.order.service.data.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.data.entity.OrderEntity;
import com.food.ordering.system.order.service.data.mapper.OrderMapper;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper<OrderEntity> orderMapper;

    @Override
    public Optional<Order> findById(OrderId id) {
        var orders = orderJpaRepository.findById(id.value());
        return orders.map(orderMapper::toOrder);
    }

    @Override
    public void save(Order order) {
        orderJpaRepository.save(orderMapper.toEntity(order));
    }
}
