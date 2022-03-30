package com.alpha.ddms.dto;

import lombok.*;

import java.util.*;

@Getter @Setter
public class ResponDto {
    private String status;
    private int code;
    private String message;
    List<ResponListDto> data;
}
