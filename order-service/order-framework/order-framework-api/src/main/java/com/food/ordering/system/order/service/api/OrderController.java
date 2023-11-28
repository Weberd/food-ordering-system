package com.food.ordering.system.order.service.api;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.dto.*;
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
        return this.orderApplicationService.createOrder(command);
    }

    @GetMapping("/{orderId}")
    public TrackOrderResponse trackOrder(@PathVariable UUID orderId) {
        return this.orderApplicationService.trackOrder(new TrackOrderQuery(new OrderId(orderId)));
    }

    @DeleteMapping("/{orderId}")
    public CancelOrderResponse cancelOrder(@PathVariable UUID orderId) {
        return this.orderApplicationService.cancelOrder(new CancelOrderCommand(new OrderId(orderId)));
    }
}
