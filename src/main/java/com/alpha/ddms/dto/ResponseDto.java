package com.alpha.ddms.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ResponseDto<T> {
    private String status;
    private int code;
    private String message;
    private T data;

}