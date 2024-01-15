package com.food.ordering.system.service;

import application.command.CancelReservationCommand;
import application.command.ReserveInventoryCommand;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.kafka.producer.KafkaProducer;
import com.food.ordering.system.order.service.application.port.output.InventoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Value("${spring.kafka.topic.restaurant-request-topic}")
    private String restaurantRequestTopic;

    private final KafkaProducer<ReserveInventoryCommand> reservationProducer;
    private final KafkaProducer<CancelReservationCommand> cancelReservationCommandKafkaProducer;

    public InventoryServiceImpl(
            KafkaProducer<ReserveInventoryCommand> reservationProducer,
            KafkaProducer<CancelReservationCommand> cancelReservationProducer
    ) {
        this.reservationProducer = reservationProducer;
        this.cancelReservationCommandKafkaProducer = cancelReservationProducer;
    }

    @Override
    public void reserveInventory(OrderId orderId) {
        reservationProducer.sendMessage(restaurantRequestTopic, new ReserveInventoryCommand(orderId));
    }

    @Override
    public void cancelInventoryReservation(OrderId orderId) {
        cancelReservationCommandKafkaProducer.sendMessage(restaurantRequestTopic, new CancelReservationCommand(orderId));
    }
}
