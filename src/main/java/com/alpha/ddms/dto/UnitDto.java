package com.alpha.ddms.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class UnitDto implements Serializable {
    private String unitCode;
    private String unitSeriesName;
    private String dealerCode;
    private int unitQuantity;
    private String unitColor;
    private String unitStatus;
    private float averageCost;
}