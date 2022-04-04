package com.alpha.ddms.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {
    private String status;
    private Integer code;
    private String message;
    private T data;
}
