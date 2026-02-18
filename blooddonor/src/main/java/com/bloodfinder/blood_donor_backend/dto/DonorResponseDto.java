package com.bloodfinder.blood_donor_backend.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonorResponseDto {

    private Long id;
    private String name;
    private String bloodGroup;
    private String phone;
    private String area;
    private String city;
    private Boolean available;
}

