package com.alpha.ddms.dto;

import lombok.Data;

@Data
public class OrderSaveDto {
    String orderId;
    String unitCode;
    String dealerCode;
    String salesId;
    String customerId;
    String minimumPayment;
    String totalValue;
    String orderValue;
    String offtheroadValue;
    String orderDiscount;
    String ppn;
    String platNomor;
    String nomorMesin;
    String nomorRangka;
    String isLeasing;
    String paymentStatus;
    String unitStatus;
}
