package com.awbd.musicshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] photo;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private Product product;

}
