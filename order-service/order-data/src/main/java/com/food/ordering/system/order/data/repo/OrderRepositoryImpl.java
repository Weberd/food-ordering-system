package com.food.ordering.system.order.data.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.mapper.OrderMapper;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;

import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderMapper orderMapper;

    public OrderRepositoryImpl(
            OrderJpaRepository orderJpaRepository,
            OrderMapper orderMapper
    ) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        var orders = orderJpaRepository.findById(id.value());
        return orders.map(orderMapper::toOrder);
    }

    @Override
    public void save(Order order) {
        orderJpaRepository.save(orderMapper.toOrderEntity(order));
    }
}
