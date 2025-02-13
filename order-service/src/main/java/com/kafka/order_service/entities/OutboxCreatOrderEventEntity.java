package com.kafka.order_service.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "outbox_create_order_event")
public class OutboxCreatOrderEventEntity  extends  BaseEntity {


    @Column(name="order_id")
    private String orderId;

    @Column(name="event_processed")
    private boolean eventProcessed;

    @Column(name="payload")
    private String orderPayLoad;

}
