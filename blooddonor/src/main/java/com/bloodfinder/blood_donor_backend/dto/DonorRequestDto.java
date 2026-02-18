package com.bloodfinder.blood_donor_backend.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DonorRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String bloodGroup;

    @NotBlank
    private String phone;

    private String area;

    private String city;

    private Double latitude;

    private Double longitude;

    private LocalDate lastDonationDate;
}
