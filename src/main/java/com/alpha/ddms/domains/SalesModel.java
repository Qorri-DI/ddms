package com.alpha.ddms.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mst_sales")
@Getter @Setter
public class SalesModel {
    @Id
    @Column(name ="sales_id", nullable = false, length = 50)
    private String sales_id;

    @Column(name = "sales_name", nullable = false, length = 255)
    private String sales_name;

    @ManyToOne
    @JoinColumn(name = "dealer_code")
    private DealerModel dealerModel;

    @ManyToOne
    @JoinColumn(name = "sales_id", insertable = false, updatable = false)
    private SalesModel salesModel;

    @Column(name = "sales_gender", nullable = false, length = 4)
    private String sales_gender;

    @Column(name = "sales_email", nullable = false, length = 255)
    private String sales_email;

    @Column(name = "sales_status", nullable = false, length = 10)
    private String sales_status;
}
