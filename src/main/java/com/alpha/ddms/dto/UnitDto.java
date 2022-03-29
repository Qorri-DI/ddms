package com.alpha.ddms.dto;

import lombok.*;

@Getter @Setter
public class UnitDto {
    private String unit_id;
    private String unit_series_name;
    private String dealerId;
    private int unit_quantity;
    private String unit_color;
    private String unit_status;
    private float average_cost;
}
