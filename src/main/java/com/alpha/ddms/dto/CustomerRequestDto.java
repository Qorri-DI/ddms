package com.alpha.ddms.dto;

public class CustomerRequestDto {
    private final String customerId;
    private final String customerName;
    private final String dealerId;
    private final String customerGender;
    private final String customerNik;
    private final String customerKk;
    private final String customerEmail;
    private final String customerAddress;

    public CustomerRequestDto(String customerId, String customerName, String dealerId, String customerGender, String customerNik, String customerKk, String customerEmail, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.dealerId = dealerId;
        this.customerGender = customerGender;
        this.customerNik = customerNik;
        this.customerKk = customerKk;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
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
}
