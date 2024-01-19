package com.food.ordering.system.order.service.data.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.data.entity.OrderOutboxEntity;
import com.food.ordering.system.order.service.data.mapper.OrderMapper;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderCreationOutboxRepositoryImpl implements OrderCreationOutboxRepository {
    private final OrderCreationOutboxJpaRepository orderCreationOutboxJpaRepository;
    private final OrderMapper<OrderOutboxEntity> orderMapper;

    @Override
    public void deleteById(OrderId id) {
        orderCreationOutboxJpaRepository.deleteById(id.value());
    }

    @Override
    public Iterable<Order> findAllBefore(Instant time) {
        var entities = orderCreationOutboxJpaRepository.findAllBefore(time);
        var orders = new ArrayList<Order>();
        for (OrderOutboxEntity entity : entities) {
            orders.add(orderMapper.toOrder(entity));
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        var orders = orderCreationOutboxJpaRepository.findById(id.value());
        return orders.map(orderMapper::toOrder);
    }

    @Override
    public void save(Order order) {
        orderCreationOutboxJpaRepository.save(orderMapper.toEntity(order));
    }
}
