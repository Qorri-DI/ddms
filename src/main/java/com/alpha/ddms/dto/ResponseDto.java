package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseDto<T> {
    private String status;
    private int code;
    private String message;
    private T data;
}
