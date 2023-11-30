package com.food.ordering.system.order.service.application.repo;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.data.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void save(Order order) {
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return Optional.empty();
    }
}
