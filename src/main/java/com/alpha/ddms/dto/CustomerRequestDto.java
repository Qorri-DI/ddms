package com.alpha.ddms.dto;

import lombok.NonNull;
import org.springframework.lang.Nullable;

public class CustomerRequestDto {
    private String customerId;
    private String customerName;
    private String dealerId;
    private String customerGender;
    private String customerNik;
    private String customerKk;
    private String customerEmail;
    private String customerAddress;
    private String customerTelp;
    private String customerHp;
    private String salesId;
    private String customerStatus;

    public CustomerRequestDto(String customerId,
                               String customerName,
                               String dealerId,
                               String customerGender,
                               String customerNik,
                               String customerKk,
                               String customerEmail,
                               String customerAddress,
                               String customerTelp,
                               String customerHp,
                               String salesId,
                               String customerStatus) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.dealerId = dealerId;
        this.customerGender = customerGender;
        this.customerNik = customerNik;
        this.customerKk = customerKk;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerTelp = customerTelp;
        this.customerHp = customerHp;
        this.salesId = salesId;
        this.customerStatus = customerStatus;
    }

    @Override
    public String toString() {
        return "CustomerRequestDto{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", dealerId='" + dealerId + '\'' +
                ", customerGender='" + customerGender + '\'' +
                ", customerNik='" + customerNik + '\'' +
                ", customerKk='" + customerKk + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerTelp='" + customerTelp + '\'' +
                ", customerHp='" + customerHp + '\'' +
                ", salesId='" + salesId + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                '}';
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDealerId() {
        return dealerId;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public String getCustomerNik() {
        return customerNik;
    }

    public String getCustomerKk() {
        return customerKk;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerTelp() {
        return customerTelp;
    }

    public String getCustomerHp() {
        return customerHp;
    }

    public String getSalesId() {
        return salesId;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
