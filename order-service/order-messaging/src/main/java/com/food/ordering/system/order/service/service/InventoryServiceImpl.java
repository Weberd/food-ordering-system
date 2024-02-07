package com.food.ordering.system.order.service.service;

import com.food.ordering.system.common.messaging.command.CancelInventoryReservationCommand;
import com.food.ordering.system.common.messaging.command.ReserveInventoryCommand;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.common.messaging.producer.KafkaProducer;
import com.food.ordering.system.order.service.application.port.output.InventoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Value("${spring.kafka.topic.restaurant-inventory-reservation-request}")
    private String inventoryReservationRequestTopic;

    @Value("${spring.kafka.topic.restaurant-inventory-reservation-cancel-request}")
    private String cancelInventoryReservationRequestTopic;

    private final KafkaProducer<ReserveInventoryCommand> reservationProducer;
    private final KafkaProducer<CancelInventoryReservationCommand> cancelReservationCommandKafkaProducer;

    public InventoryServiceImpl(
            KafkaProducer<ReserveInventoryCommand> reservationProducer,
            KafkaProducer<CancelInventoryReservationCommand> cancelReservationProducer
    ) {
        this.reservationProducer = reservationProducer;
        this.cancelReservationCommandKafkaProducer = cancelReservationProducer;
    }

    @Override
    public void reserveInventory(OrderId orderId) {
        reservationProducer.sendMessage(inventoryReservationRequestTopic, new ReserveInventoryCommand(orderId));
    }

    @Override
    public void cancelInventoryReservation(OrderId orderId) {
        cancelReservationCommandKafkaProducer.sendMessage(cancelInventoryReservationRequestTopic, new CancelInventoryReservationCommand(orderId));
    }
}
