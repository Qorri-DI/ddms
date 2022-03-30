package com.alpha.ddms.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trx_order")
@Getter @Setter
public class OrderModel {
    @Id
    @Column(name = "order_id", nullable = false, length = 50)
    private String order_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private UnitModel unitModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    private DealerModel dealerModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sales_id")
    private SalesModel salesModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerModel listCustomerModel;

    @Column(name = "minimum_payment", nullable = false)
    private float minimum_payment;

    @Column(name = "total_value", nullable = false)
    private float total_value;

    @Column(name = "order_value", nullable = false)
    private float order_value;

    @Column(name = "offtheroad_value", nullable = false)
    private float offtheroad_value;

    @Column(name = "order_total_discount", nullable = false)
    private float order_total_discount;

    @Column(name = "ppn", nullable = false)
    private float ppn;

    @Column(name = "plat_nomor", nullable = true, length = 50)
    private String plat_nomor;

    @Column(name = "nomor_mesin", nullable = true, length = 50)
    private String nomor_mesin;

    @Column(name = "nomor_rangka", nullable = true, length = 50)
    private String nomor_rangka;

    @Column(name = "is_leasing", nullable = true, length = 10)
    private String is_leasing;

    @Column(name = "payment_status", nullable = false, length = 50)
    private String payment_status;

    @Column(name = "unit_status", nullable = false, length = 50)
    private String unit_status;


}
