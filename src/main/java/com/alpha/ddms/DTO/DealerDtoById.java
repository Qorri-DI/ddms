package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class DealerDtoById {
    private String status;
    private int code;
    private String message;
    DealerDTO data;

}
