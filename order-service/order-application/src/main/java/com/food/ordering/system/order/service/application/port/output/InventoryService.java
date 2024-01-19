package com.food.ordering.system.order.service.application.port.output;

import com.food.ordering.system.domain.value.OrderId;

public interface InventoryService {
    void reserveInventory(OrderId orderId);
    void cancelInventoryReservation(OrderId orderId);
}
