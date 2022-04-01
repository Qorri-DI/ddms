package com.alpha.ddms.models;

import com.alpha.ddms.domains.DealerModel;
import com.alpha.ddms.domains.SalesModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Getter @Setter
public class ViewSales {

    @Id
    @Column(name ="sales_id")
    @JsonProperty(value = "salesId")
    private String sales_id;

    @Column(name = "sales_name", nullable = false, length = 255)
    @JsonProperty(value = "salesName")
    private String sales_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    @JsonProperty(value = "dealerId")
    private DealerModel dealerModel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "sales_id")
    @JsonProperty(value = "supervisorId")
    private SalesModel salesModel;

    @Column(name = "sales_gender", nullable = false, length = 4)
    @JsonProperty(value = "salesGender")
    private String sales_gender;

    @Column(name = "sales_email", nullable = false, length = 255)
    @JsonProperty(value = "salesEmail")
    private String sales_email;

    @Column(name = "sales_status", nullable = false, length = 10)
    @JsonProperty(value = "salesStatus")
    private String sales_status;

    public String getSalesModel() {
        return salesModel.getSales_id();
    }

    public String getDealerModel() {
        return dealerModel.getDealer_code();
    }
}
