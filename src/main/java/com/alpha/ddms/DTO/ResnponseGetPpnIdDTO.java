package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResnponseGetPpnIdDTO<T> {

    private String statuss;
    private int code;
    private String message;
    private T data;


}
