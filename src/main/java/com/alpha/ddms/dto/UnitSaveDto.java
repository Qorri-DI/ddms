package com.alpha.ddms.dto;

import lombok.*;

@Getter @Setter
public class UnitSaveDto<T> {
    private int code;
    private T data;
    private String message;
    private String status;
}
