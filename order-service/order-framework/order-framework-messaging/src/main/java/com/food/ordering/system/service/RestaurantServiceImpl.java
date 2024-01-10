package com.food.ordering.system.service;

import application.command.CancelReservationCommand;
import application.command.ReserveInventoryCommand;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.kafka.KafkaProducer;
import com.food.ordering.system.order.service.application.port.output.RestaurantService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Value("${spring.kafka.topic.restaurant-request-topic}")
    private String restaurantRequestTopic;

    private final KafkaProducer<ReserveInventoryCommand> reservationProducer;
    private final KafkaProducer<CancelReservationCommand> cancelReservationCommandKafkaProducer;

    public RestaurantServiceImpl(
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
