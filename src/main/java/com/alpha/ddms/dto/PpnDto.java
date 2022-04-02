package com.alpha.ddms.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class PpnDto {
    private String id;
    private String dealerId;
    private String ppnDescription;
    private float ppnRate;
    private float ppnRatePrevious;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private String status;
}
