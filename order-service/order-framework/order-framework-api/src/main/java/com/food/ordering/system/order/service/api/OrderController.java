package com.food.ordering.system.order.service.api;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.application.dto.*;
import com.food.ordering.system.order.service.application.port.input.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody @Validated CreateOrderCommand command) {
        return  orderApplicationService.createOrder(command);
    }

    @GetMapping("/{orderId}")
    public TrackOrderResponse trackOrder(@PathVariable UUID orderId) {
        return this.orderApplicationService.trackOrder(new TrackOrderQuery(new OrderId(orderId)));
    }

    @DeleteMapping("/{orderId}")
    public CancelOrderResponse cancelOrder(@PathVariable UUID orderId) {
        return this.orderApplicationService.cancelOrder(new CancelOrderCommand(new OrderId(orderId)));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException exception) {
        return exception.getMessage();
    }
}
