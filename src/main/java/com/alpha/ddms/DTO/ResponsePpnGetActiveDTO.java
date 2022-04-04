package com.alpha.ddms.dto;

import com.alpha.ddms.domains.PpnModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class ResponsePpnGetActiveDTO {

    private String statuss;
    private int code;
    private String message;

    private String id;
    private String dealerId;
    private String ppnDescription;
    private float ppnRate;
    private float ppnRatePrevious;
    private Date effectiveStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm",timezone = "GMT+7")
    private Date effectiveeEndtDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm",timezone = "GMT+7")
    private String status;
}
