package com.alpha.ddms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOrderRequestDto {
    private String platNomor;
    private String dealerId;
    private String nomorMesin;
    private String nomorRangka;
    private String paymentStatus;
    private Integer offset;
    private Integer limit;
}
