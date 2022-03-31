package com.alpha.ddms.dto;

import java.util.List;

public class DealerDtoPost {
    private int code;
    List<DealerDTO> data;
    private String massage;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DealerDTO> getData() {
        return data;
    }

    public void setData(List<DealerDTO> data) {
        this.data = data;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
