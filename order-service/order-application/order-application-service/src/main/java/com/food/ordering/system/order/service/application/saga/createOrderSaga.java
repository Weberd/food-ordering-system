package com.food.ordering.system.order.service.application.saga;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.PaymentService;
import com.food.ordering.system.order.service.application.port.output.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class createOrderSaga {

    @Autowired
    private final RestaurantService restaurantService;
    @Autowired
    private final PaymentService paymentService;

    public createOrderSaga(RestaurantService restaurantService, PaymentService paymentService) {
        this.restaurantService = restaurantService;
        this.paymentService = paymentService;
    }

    void placeOrder(Order order) {
        reserveInventory(order);
        processPayment(order);
    }

    void reserveInventory(Order order) {
        try {
            restaurantService.reserveInventory(order.id());
        } catch (Exception e) {
            log.error("Inventory reservation failed: " + e.getMessage(), order);
            rollbackInventoryReservation(order.id());
        }
    }

    void processPayment(Order order) {
        try {
            paymentService.processPayment(order.id());
        } catch (Exception e) {
            log.error("Process payment failed: " + e.getMessage(), order);
            rollbackPayment(order.id());
        }
    }

    void rollbackInventoryReservation(OrderId orderId) {
        try {
            restaurantService.cancelInventoryReservation(orderId);
        } catch (Exception e) {
            log.error("Place order ROLLBACK failed: " + e.getMessage(), orderId);
        }
    }

    void rollbackPayment(OrderId orderId) {
        try {
            paymentService.cancelPayment(orderId);
        } catch (Exception e) {
            log.error("Place order ROLLBACK failed: " + e.getMessage(), orderId);
        }
    }
}
