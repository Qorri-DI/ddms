package com.alpha.ddms.dto;

import lombok.*;

import java.util.List;
@Getter @Setter
public class DealerDtoPost {
    private int code;
    List<DealerDTO> data;
    private String massage;
    private String status;
}
