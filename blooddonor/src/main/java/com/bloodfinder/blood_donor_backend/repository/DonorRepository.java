package com.bloodfinder.blood_donor_backend.repository;


import com.bloodfinder.blood_donor_backend.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    Optional<Donor> findByEmail(String email);

    List<Donor> findByBloodGroupAndAreaAndAvailableTrue(String bloodGroup, String area);

    List<Donor> findByBloodGroupAndAvailableTrue(String bloodGroup);
}

