package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DealerDtoListAll {
    private String status;
    private int code;
    private String message;
    DealerDTOlist data;
}
