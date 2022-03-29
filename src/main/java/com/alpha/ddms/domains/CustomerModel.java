package com.alpha.ddms.domains;

import lombok.*;

import javax.persistence.*;

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
}
