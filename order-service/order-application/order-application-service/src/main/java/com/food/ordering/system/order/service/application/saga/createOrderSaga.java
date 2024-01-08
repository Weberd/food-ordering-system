package com.food.ordering.system.order.service.application.saga;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.entity.Order;
import com.food.ordering.system.order.service.application.port.output.PaymentService;
import com.food.ordering.system.order.service.application.port.output.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class createOrderSaga {

    private final RestaurantService restaurantService;
    private final PaymentService paymentService;

    public createOrderSaga(RestaurantService restaurantService, PaymentService paymentService) {
        this.restaurantService = restaurantService;
        this.paymentService = paymentService;
    }

    void placeOrder(Order order) {
        try {
            restaurantService.reserveInventory(order.id());
            paymentService.validatePayment(order.id());
        } catch (Exception e) {
            log.error("Place order failed: " + e.getMessage(), order);
            rollback(order.id());
        }
    }

    void rollback(OrderId orderId) {
        try {
            restaurantService.cancelInventoryReservation(orderId);
        } catch (Exception e) {
            log.error("Place order ROLLBACK failed: " + e.getMessage(), orderId);
        }
    }
}
