package com.bloodfinder.blood_donor_backend.service;


import com.bloodfinder.blood_donor_backend.dto.DonorRequestDto;
import com.bloodfinder.blood_donor_backend.dto.DonorResponseDto;

import java.util.List;

public interface DonorService {

    DonorResponseDto register(DonorRequestDto dto);

    List<DonorResponseDto> search(String bloodGroup, String area);

    List<DonorResponseDto> searchNearby(String bloodGroup,
                                        Double lat,
                                        Double lon,
                                        Double radius);

    String updateAvailability(Long id, Boolean status);

    String updateDonationDate(Long id);
}

