package com.alpha.ddms.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_ppn")
@Getter @Setter
public class PpnModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ppn_id", nullable = false, length = 50)
    private String ppn_id;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_code")
    private DealerModel dealerModel;

    @Column(name = "effective_start_date", nullable = false)
    private Date effective_start_date;

    @Column(name = "effective_end_date", nullable = true)
    private Date effective_end_date;

    @Column(name = "ppn_rate", nullable = false)
    private float ppn_rate;

    @Column(name = "ppn_rate_previous", nullable = true)
    private float ppn_rate_previous;

    @Column(name = "ppn_status", nullable = false, length = 10)
    private String ppn_status;
}
