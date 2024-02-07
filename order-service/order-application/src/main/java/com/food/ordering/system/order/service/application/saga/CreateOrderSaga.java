package com.food.ordering.system.order.service.application.saga;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.OrderCreationOutboxRepository;
import com.food.ordering.system.order.service.application.port.output.OrderRepository;
import com.food.ordering.system.order.service.application.port.output.PaymentService;
import com.food.ordering.system.order.service.application.port.output.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderSaga {
    private final InventoryService restaurantService;
    private final PaymentService paymentService;
    private final TransactionTemplate transactionTemplate;
    private final OrderRepository orderRepository;
    private final OrderCreationOutboxRepository orderCreationOutboxRepository;

    public void placeOrder(Order order) {
        try {
            this.transactionTemplate.executeWithoutResult(transactionStatus -> {
                var existingOrderOutbox = orderCreationOutboxRepository.findById(order.id());

                if (existingOrderOutbox.isEmpty()) {
                    orderCreationOutboxRepository.save(order);
                }

                var existingOrder = orderRepository.findById(order.id());

                if (existingOrder.isEmpty()) {
                    orderRepository.save(order);
                }
            });

            reserveInventory(order);
            processPayment(order);

            orderCreationOutboxRepository.deleteById(order.id());
        } catch (Exception e) {
            log.error("Place order failed: " + e.getMessage(), order);
        }
    }

    private void reserveInventory(Order order) {
        try {
            log.info("Reserving inventory for order " + order.id());
            restaurantService.reserveInventory(order.id());
        } catch (Exception e) {
            log.error("Inventory reservation failed: " + e.getMessage(), order);
            rollbackInventoryReservation(order.id());
            throw e;
        }
    }

    private void processPayment(Order order) {
        try {
            log.info("Processing payment for order " + order.id());
            paymentService.processPayment(order.id());
        } catch (Exception e) {
            log.error("Process payment failed: " + e.getMessage(), order);
            rollbackPayment(order.id());
            throw e;
        }
    }

    private void rollbackInventoryReservation(OrderId orderId) {
        try {
            restaurantService.cancelInventoryReservation(orderId);
        } catch (Exception e) {
            log.error("Place order ROLLBACK failed: " + e.getMessage(), orderId);
        }
    }

    private void rollbackPayment(OrderId orderId) {
        try {
            paymentService.cancelPayment(orderId);
        } catch (Exception e) {
            log.error("Place order ROLLBACK failed: " + e.getMessage(), orderId);
        }
    }
}
