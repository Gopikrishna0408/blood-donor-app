package com.bloodfinder.blood_donor_backend.service.impl;


import com.bloodfinder.blood_donor_backend.dto.*;
import com.bloodfinder.blood_donor_backend.entity.Donor;
import com.bloodfinder.blood_donor_backend.repository.DonorRepository;
import com.bloodfinder.blood_donor_backend.service.DonorService;
import com.bloodfinder.blood_donor_backend.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public DonorResponseDto register(DonorRequestDto dto) {

        Donor donor = Donor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .bloodGroup(dto.getBloodGroup())
                .phone(dto.getPhone())
                .area(dto.getArea())
                .city(dto.getCity())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .lastDonationDate(dto.getLastDonationDate())
                .available(true)
                .createdAt(LocalDateTime.now())
                .build();

        donorRepository.save(donor);

        return map(donor);
    }

    @Override
    public List<DonorResponseDto> search(String bloodGroup, String area) {
        return donorRepository
                .findByBloodGroupAndAreaAndAvailableTrue(bloodGroup, area)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonorResponseDto> searchNearby(String bloodGroup,
                                               Double lat,
                                               Double lon,
                                               Double radius) {

        return donorRepository
                .findByBloodGroupAndAvailableTrue(bloodGroup)
                .stream()
                .filter(d -> DistanceUtil.calculateDistance(
                        lat, lon,
                        d.getLatitude(), d.getLongitude()) <= radius)
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public String updateAvailability(Long id, Boolean status) {

        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        donor.setAvailable(status);
        donorRepository.save(donor);

        return "Availability updated successfully";
    }

    @Override
    public String updateDonationDate(Long id) {

        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        if (donor.getLastDonationDate() != null &&
                donor.getLastDonationDate().plusDays(90)
                        .isAfter(LocalDate.now())) {

            return "Donor not eligible yet (90 days rule)";
        }

        donor.setLastDonationDate(LocalDate.now());
        donorRepository.save(donor);

        return "Donation date updated";
    }

    private DonorResponseDto map(Donor donor) {
        return DonorResponseDto.builder()
                .id(donor.getId())
                .name(donor.getName())
                .bloodGroup(donor.getBloodGroup())
                .phone(donor.getPhone())
                .area(donor.getArea())
                .city(donor.getCity())
                .available(donor.getAvailable())
                .build();
    }
}
