package com.foodrescue.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ngos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NGO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ngoName;

    private String contactPerson;

    private String phone;

    private String email;

    private String address;

    private Double latitude;

    private Double longitude;

    private Boolean verified;
}