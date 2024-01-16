package com.food.ordering.system.order.service.data.entity;

import com.food.ordering.system.domain.value.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "orders")
public final class OrderEntity {
    @Id
    @Column
    private UUID id;

    @Column
    private String description;

    @Column
    @Enumerated
    private OrderStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

    public OrderEntity(UUID id, String description, OrderStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
}
