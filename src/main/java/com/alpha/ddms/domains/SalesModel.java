package com.alpha.ddms.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mst_sales")
@Getter @Setter
public class SalesModel implements Serializable {
    @Id
    @Column(name ="sales_id", nullable = false, length = 50)
    private String sales_id;

    @Column(name = "sales_name", nullable = false, length = 255)
    private String sales_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    private DealerModel dealerModel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "sales_id")
    @JsonProperty(value = "supervisor_id")
    private SalesModel salesModel;

    @Column(name = "sales_gender", nullable = false, length = 4)
    private String sales_gender;

    @Column(name = "sales_email", nullable = false, length = 255)
    private String sales_email;

    @Column(name = "sales_status", nullable = false, length = 10)
    private String sales_status;

    public String getSalesModel() {
        return salesModel.getSales_id();
    }

    public String getDealerModel() {
        return dealerModel.getDealer_code();
    }
}
