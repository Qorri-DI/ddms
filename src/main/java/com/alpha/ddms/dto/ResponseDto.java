package com.alpha.ddms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
