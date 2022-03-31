package com.alpha.ddms.domains;

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
    private String customer_id;

    @Column(name = "customer_name", nullable = false, length = 255)
    private String customer_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    private DealerModel dealerModel;

    @Column(name = "customer_gender", nullable = false, length = 4)
    private String customer_gender;

    @Column(name = "customer_nik", nullable = false, length = 50)
    private String customer_nik;

    @Column(name = "customer_kk", nullable = false, length = 50)
    private String customer_kk;

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "customer_email", nullable = true, length = 255)
    private String customer_email;

    @Column(name = "customer_address", nullable = false, length = 512)
    private String customer_address;

    @Column(name = "customer_telp_number", nullable = true, length = 50)
    private String customer_telp_number;

    @Column(name = "customer_hp_number", nullable = true, length = 50)
    private String customer_hp_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_id")
    private SalesModel salesModel;

    @Column(name = "customer_status", nullable = false, length = 10)
    private String customer_status;

    public String getDealerModel() {
        return dealerModel.getDealer_code();
    }

//    public String getSalesModel() {
//        return salesModel.getSales_id();
//    }
}
