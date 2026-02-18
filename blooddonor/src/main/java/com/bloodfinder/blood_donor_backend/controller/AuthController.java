package com.bloodfinder.blood_donor_backend.controller;


import com.bloodfinder.blood_donor_backend.config.JwtUtil;
import com.bloodfinder.blood_donor_backend.dto.*;
import com.bloodfinder.blood_donor_backend.repository.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final DonorRepository donorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        var donor = donorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(),
                donor.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(donor.getEmail());

        return new AuthResponse(token);
    }
}

