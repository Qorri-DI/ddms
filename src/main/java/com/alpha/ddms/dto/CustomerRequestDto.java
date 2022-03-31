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
                              @NonNull String customerName,
                              @NonNull String dealerId,
                              @NonNull String customerGender,
                              @NonNull String customerNik,
                              @NonNull String customerKk,
                              @NonNull String customerEmail,
                              @NonNull String customerAddress,
                              @NonNull String customerTelp,
                              @NonNull String customerHp,
                              @NonNull String salesId,
                              @NonNull String customerStatus) {
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
