package com.alpha.ddms.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private String status;
    private Integer code;
    private String message;
    private T data;

    public ResponseDto(String status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
