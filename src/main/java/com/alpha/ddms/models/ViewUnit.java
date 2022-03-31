package com.alpha.ddms.models;

import com.alpha.ddms.domains.DealerModel;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Getter @Setter
public class ViewUnit{
    @Id
    @Column(name = "unit_id", nullable = false, length = 50)
    private String unit_id;

    @Column(name = "unit_series_name", nullable = false, length = 255)
    private String unit_series_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    private DealerModel dealerModel;

    @Column(name = "unit_quantity", nullable = false)
    private int unit_quantity;

    @Column(name = "unit_color", nullable = false, length = 512)
    private String unit_color;

    @Column(name = "unit_status", nullable = false, length = 10)
    private String unit_status;

    @Column(name = "average_cost", nullable = false)
    private float average_cost;

}
