package com.alpha.ddms.dto;

import lombok.*;

@Getter @Setter
public class UnitDto {
    private String unitCode;
    private String unitSeriesName;
    private String dealerCode;
    private int unitQuantity;
    private String unitColor;
    private String unitStatus;
    private float averageCost;
}
