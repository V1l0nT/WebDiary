package org.example.userservice.service;

import lombok.AllArgsConstructor;
import org.example.userservice.dto.DiaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class DiaryServiceClient {

    @Autowired
    private final RestTemplate restTemplate;

    public DiaryDTO getDiaryById(Long diaryId) {
        return restTemplate.getForObject("http://diary-service:8082/diary/{diaryId}", DiaryDTO.class, diaryId);
    }
}

