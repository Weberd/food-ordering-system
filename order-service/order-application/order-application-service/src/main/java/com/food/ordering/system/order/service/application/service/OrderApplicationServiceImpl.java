package com.food.ordering.system.order.service.application.service;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.dto.*;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.input.OrderApplicationService;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public final class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderRepository orderRepository;
    private final OrderCreationOutboxRepository orderCreationOutboxRepository;
    private final TransactionTemplate transactionTemplate;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        var order = new Order(new OrderId(UUID.randomUUID()), command.getDescription(), OrderStatus.PENDING);

        this.transactionTemplate.executeWithoutResult(transactionStatus -> {
            orderRepository.save(order);
            this.orderCreationOutboxRepository.save(order);
        });

        // TODO: send order created message;
        this.orderCreationOutboxRepository.deleteById(order.id());

        return new CreateOrderResponse(order.id());
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) throws NoSuchElementException {
        var order = orderRepository.findById(query.orderId()).orElseThrow(() -> new NoSuchElementException(String.valueOf(query.orderId())));
        return new TrackOrderResponse(order.id(), order.getStatus());
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderCommand command) throws NoSuchElementException {
        var order = orderRepository.findById(command.orderId()).orElseThrow(() -> new NoSuchElementException(String.valueOf(command.orderId())));
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
        return new CancelOrderResponse(command.orderId());
    }
}
