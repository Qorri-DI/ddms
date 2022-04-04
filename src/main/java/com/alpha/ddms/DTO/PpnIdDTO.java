package com.alpha.ddms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class PpnIdDTO {
    private String id;
    private String dealerId;
    private String ppnDescription;
    private float ppnRate;
    private float ppnRatePrevious;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm",timezone = "GMT+7")
    private Date effectiveStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm",timezone = "GMT+7")
    private Date effectiveEndDate;

    private String status;
}
