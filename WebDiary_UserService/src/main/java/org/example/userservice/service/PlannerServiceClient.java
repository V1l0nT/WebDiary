package org.example.userservice.service;

import lombok.AllArgsConstructor;
import org.example.userservice.dto.PlannerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PlannerServiceClient {

    @Autowired
    private final RestTemplate restTemplate;

    public PlannerDTO getPlannerById(Long plannerId) {
        return restTemplate.getForObject("http://planner-service:8083/planner/{plannerId}", PlannerDTO.class, plannerId);
    }
}