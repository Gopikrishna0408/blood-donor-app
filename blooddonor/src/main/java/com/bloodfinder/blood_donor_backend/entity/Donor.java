package com.bloodfinder.blood_donor_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String bloodGroup;

    @Column(nullable = false)
    private String phone;

    private String area;

    private String city;

    private Double latitude;

    private Double longitude;

    private LocalDate lastDonationDate;

    private Boolean available;

    private LocalDateTime createdAt;
    
    @Enumerated(EnumType.STRING)
    private Role role;

}
