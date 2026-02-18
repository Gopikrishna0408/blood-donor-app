package com.bloodfinder.blood_donor_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodfinder.blood_donor_backend.service.SnsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/emergency")
@RequiredArgsConstructor
public class EmergencyController {

    private final SnsService snsService;

    @PostMapping
    public String sendAlert(@RequestBody String message) {
        snsService.sendEmergencyAlert(message);
        return "Emergency email sent successfully!";
    }
}
