package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class DealerDtoGet {
    private String status;
    private int code;
    private String massage;
    List<DealerDTOlist> data;


}
