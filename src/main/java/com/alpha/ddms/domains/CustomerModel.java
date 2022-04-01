package com.alpha.ddms.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mst_customer")
@Getter @Setter
public class CustomerModel {
    @Id
    @Column(name = "customer_id", nullable = false, length = 50)
    @JsonProperty("customerId")
    private String customer_id;


    @Column(name = "customer_name", nullable = false, length = 255)
    @JsonProperty("customerName")
    private String customer_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    @JsonProperty("dealerCode")
    private DealerModel dealerModel;

    @Column(name = "customer_gender", nullable = false, length = 4)
    @JsonProperty("customerGender")
    private String customer_gender;

    @JsonProperty("customerNik")
    @Column(name = "customer_nik", nullable = false, length = 50)
    private String customer_nik;

    @JsonProperty("customerKk")
    @Column(name = "customer_kk", nullable = false, length = 50)
    private String customer_kk;

    @JsonProperty("customerEmail")
    @Column(name = "customer_email", nullable = true, length = 255)
    private String customer_email;

    @JsonProperty("customerAddress")
    @Column(name = "customer_address", nullable = false, length = 512)
    private String customer_address;

    @JsonProperty("customerTelpNumber")
    @Column(name = "customer_telp_number", nullable = true, length = 50)
    private String customer_telp_number;

    @JsonProperty("customerHpNumber")
    @Column(name = "customer_hp_number", nullable = true, length = 50)
    private String customer_hp_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_id")
    @JsonProperty("salesModel")
    private SalesModel salesModel;

    @JsonProperty("customerStatus")
    @Column(name = "customer_status", nullable = false, length = 10)
    private String customer_status;

    public String getDealerModel() {
        return dealerModel.getDealer_code();
    }

    public String getSalesModel(){
        if (this.salesModel == null){
            return "kosong";
        }
        return salesModel.getSales_id();
    }
}
