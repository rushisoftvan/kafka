package com.kafka.order_service.enums;

public enum OrderStatusEnum {

    PENDING("PENDING"),DELIVERED("DELIVERED"), SHIPPED("SHIPPED"), CANCELLED("CANCELLED") ;

    private String value;

     OrderStatusEnum(String value){
         this.value = value;
    }

    public String value(){
        return value;
    }
}
