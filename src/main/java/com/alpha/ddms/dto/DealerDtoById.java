package com.alpha.ddms.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
public class DealerDtoById {
    private String status;
    private int code;
    private String message;
    DealerDTO data;

}