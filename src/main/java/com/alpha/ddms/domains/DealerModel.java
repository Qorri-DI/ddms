package com.alpha.ddms.domains;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mst_dealer")
@Getter @Setter
public class DealerModel {
    @Id
    @Column(name = "dealer_code", nullable = false, length = 50)
    private String dealer_code;

    @Column(name = "dealer_name", nullable = false, length = 255)
    private String dealer_name;

    @Column(name = "dealer_class", nullable = false, length = 10)
    private String dealer_class;

    @Column(name = "telp_number", nullable = false, length = 255)
    private String telp_number;

    @Column(name = "alamat", nullable = false, length = 512)
    private String alamat;

    @Column(name = "dealer_status", nullable = false, length = 10)
    private String dealer_status;

    @Column(name = "dealer_ext_code", nullable = true, length = 50)
    private String dealer_ext_code;
}
