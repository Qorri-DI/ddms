package com.alpha.ddms.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class DealerDtoGet<T>{
    private String status;
    private int code;
    private String massage;
    private T data;
}
