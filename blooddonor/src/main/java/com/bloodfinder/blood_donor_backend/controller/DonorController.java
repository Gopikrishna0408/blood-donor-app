package com.bloodfinder.blood_donor_backend.controller;


import com.bloodfinder.blood_donor_backend.dto.*;
import com.bloodfinder.blood_donor_backend.service.DonorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
@RequiredArgsConstructor
public class DonorController {

    private final DonorService donorService;

    @PostMapping("/register")
    public DonorResponseDto register(
            @Valid @RequestBody DonorRequestDto dto) {

        return donorService.register(dto);
    }

    @GetMapping("/search")
    public List<DonorResponseDto> search(
            @RequestParam String bloodGroup,
            @RequestParam String area) {

        return donorService.search(bloodGroup, area);
    }

    @GetMapping("/nearby")
    public List<DonorResponseDto> nearby(
            @RequestParam String bloodGroup,
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam Double radius) {

        return donorService.searchNearby(bloodGroup, lat, lon, radius);
    }

    @PutMapping("/{id}/availability")
    public String updateAvailability(
            @PathVariable Long id,
            @RequestParam Boolean status) {

        return donorService.updateAvailability(id, status);
    }

    @PutMapping("/{id}/donation")
    public String updateDonation(@PathVariable Long id) {
        return donorService.updateDonationDate(id);
    }
}

