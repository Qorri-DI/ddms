package com.alpha.ddms.DTO;

import java.util.List;

public class DealerDtoById {
    private String status;
    private int code;
    private String message;
    List<DealerDTO> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DealerDTO> getData() {
        return data;
    }

    public void setData(List<DealerDTO> data) {
        this.data = data;
    }
}
