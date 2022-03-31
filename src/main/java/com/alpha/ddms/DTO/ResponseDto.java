package com.alpha.ddms.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private String status;
    private Integer code;
    private String message;
    private T data;

}
