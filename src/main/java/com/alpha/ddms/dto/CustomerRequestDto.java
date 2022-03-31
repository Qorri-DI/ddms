package com.alpha.ddms.dto;

import org.springframework.lang.Nullable;

public class CustomerRequestDto {
    private final String customerId;
    private final String customerName;
    private final String dealerId;
    private final String customerGender;
    private final String customerNik;
    private final String customerKk;
    private final String customerEmail;
    private final String customerAddress;
    private final String customerTelp;
    private final String customerHp;
    private final String salesId;
    private final String customerStatus;

    public CustomerRequestDto(String customerId,
                              @Nullable String customerName,
                              @Nullable String dealerId,
                              @Nullable String customerGender,
                              @Nullable String customerNik,
                              @Nullable String customerKk,
                              @Nullable String customerEmail,
                              @Nullable String customerAddress,
                              @Nullable String customerTelp,
                              @Nullable String customerHp,
                              @Nullable String salesId,
                              @Nullable String customerStatus) {
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
}