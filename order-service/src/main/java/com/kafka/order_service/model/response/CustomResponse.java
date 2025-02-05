package com.kafka.order_service.model.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CustomResponse {

    private Object data;

    private String message;

    private Integer code;

    boolean success ;


    public static CustomResponse success (Object data , String message){
        return builder().success(true)
                .data(data)
                .code(HttpStatus.OK.value())
                .message(message)
                .build();
    }

}
