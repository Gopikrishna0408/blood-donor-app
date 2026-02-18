package com.bloodfinder.blood_donor_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@RequiredArgsConstructor
public class SnsService {

    private final SnsClient snsClient;

    @Value("${sns.topic.arn}")
    private String topicArn;


    public void sendEmergencyAlert(String message) {

        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .subject("Blood Emergency Alert")
                .message(message)
                .build();

        snsClient.publish(request);
    }
}

