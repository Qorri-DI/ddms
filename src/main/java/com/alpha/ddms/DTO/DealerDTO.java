package com.alpha.ddms.dto;

public class DealerDTO {
    private String dealerId;
    private String dealerName;
    private String dealerClass;
    private String telpNumber;
    private String alamat;
    private String dealerExCode;
    private String dealerStatus;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerClass() {
        return dealerClass;
    }

    public void setDealerClass(String dealerClass) {
        this.dealerClass = dealerClass;
    }

    public String getTelpNumber() {
        return telpNumber;
    }

    public void setTelpNumber(String telpNumber) {
        this.telpNumber = telpNumber;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDealerExCode() {
        return dealerExCode;
    }

    public void setDealerExCode(String dealerExCode) {
        this.dealerExCode = dealerExCode;
    }

    public String getDealerStatus() {
        return dealerStatus;
    }

    public void setDealerStatus(String dealerStatus) {
        this.dealerStatus = dealerStatus;
    }
}
