package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter
public class DealerDtoPost {
    private int code;
    List<DealerDTO> data;
    private String massage;
    private String status;

}
