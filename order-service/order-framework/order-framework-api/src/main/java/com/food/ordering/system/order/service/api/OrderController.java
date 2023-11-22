package com.food.ordering.system.order.service.api;

import com.food.ordering.system.order.service.application.dto.CreateOrderCommand;
import com.food.ordering.system.order.service.application.dto.CreateOrderResponse;
import com.food.ordering.system.order.service.application.dto.TrackOrderResponse;
import com.food.ordering.system.order.service.application.port.input.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody CreateOrderCommand command) {
        return new CreateOrderResponse();
    }

    @GetMapping("/{trackingId}")
    public TrackOrderResponse trackOrder(@PathVariable UUID trackingId) {
        return new TrackOrderResponse(UUID.randomUUID(), "PENDING");
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.GONE)
    public CancelOrderResponse cancelOrder(@PathVariable UUID orderId) {
        return this.orderApplicationService.cancelOrder(new CancelOrderCommand(orderId));
    }
}
