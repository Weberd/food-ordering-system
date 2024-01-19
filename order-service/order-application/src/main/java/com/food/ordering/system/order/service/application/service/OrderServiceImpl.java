package com.food.ordering.system.order.service.application.service;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.domain.value.OrderStatus;
import com.food.ordering.system.order.service.application.dto.*;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.input.OrderService;
import com.food.ordering.system.order.service.application.port.output.InventoryService;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;
import com.food.ordering.system.order.service.application.port.output.PaymentService;
import com.food.ordering.system.order.service.application.saga.CreateOrderSaga;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public final class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final CreateOrderSaga createOrderSaga;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        var order = new Order(new OrderId(UUID.randomUUID()), command.getDescription(), OrderStatus.PENDING);
        createOrderSaga.placeOrder(order);
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

        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.save(order);
        }

        if (order.getStatus() == OrderStatus.APPROVED) {
            inventoryService.cancelInventoryReservation(command.orderId());
            order.setStatus(OrderStatus.CANCELING);
            orderRepository.save(order);
        }

        if (order.getStatus() == OrderStatus.PAID) {
            paymentService.cancelPayment(command.orderId());
            order.setStatus(OrderStatus.CANCELING);
            orderRepository.save(order);
        }

        return new CancelOrderResponse(command.orderId());
    }

    @Override
    public void updateStatus(OrderId orderId, OrderStatus status) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException(String.valueOf(orderId)));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
