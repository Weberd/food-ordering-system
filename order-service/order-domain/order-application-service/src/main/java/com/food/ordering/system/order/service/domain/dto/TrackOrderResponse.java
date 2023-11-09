package com.food.ordering.system.order.service.domain.dto;

import java.util.UUID;

public record TrackOrderResponse(UUID trackingId, String status) {
}
