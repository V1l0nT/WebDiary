package org.example.plannerservice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.plannerservice.dto.DiaryDTO;
import org.example.plannerservice.dto.UserDTO;
import org.example.plannerservice.entity.PlannerEntity;
import org.example.plannerservice.repository.PlannerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlannerService {
    private final UserServiceClient userServiceClient;
    private final DiaryServiceClient diaryServiceClient;
    private final PlannerRepository plannerRepository;

    @Transactional
    public PlannerEntity addToList(String name, Long diaryId) {
        Long userId = getUserIdByName(name);
        UserDTO user = userServiceClient.getUserById(userId);
        DiaryDTO diary = diaryServiceClient.getDiaryById(diaryId);
        System.out.println(diary.getDescription());
        if (!plannerRepository.findAll().contains(findPlannerItem(userId, diaryId))) {
            int totalEpisodes = 0;
            for (Integer episodes : diary.getEpisodes().values()) {
                totalEpisodes += episodes;
            }
            PlannerEntity planner = PlannerEntity.builder()
                    .userId(userId)
                    .diaryId(diaryId)
                    .build();
            return plannerRepository.save(planner);
        }
        return null;
    }

    public PlannerEntity addToListByUserID(Long userId, Long diaryId) {
        UserDTO user = userServiceClient.getUserById(userId);
        DiaryDTO diary = diaryServiceClient.getDiaryById(diaryId);
        System.out.println(diary.getDescription());
        if (!plannerRepository.findAll().contains(findPlannerItem(userId, diaryId))) {
            int totalEpisodes = 0;
            for (Integer episodes : diary.getEpisodes().values()) {
                totalEpisodes += episodes;
            }
            PlannerEntity planner = PlannerEntity.builder()
                    .userId(userId)
                    .diaryId(diaryId)
                    .build();
            return plannerRepository.save(planner);
        }
        return null;
    }

    public PlannerEntity findPlannerItem(Long userId, Long diaryId) {
        return plannerRepository.findByUserIdAndDiaryId(userId, diaryId);
    }

    public Optional<PlannerEntity> findPlannerById(Long id) {
        return plannerRepository.findById(id);
    }

    public Long getUserIdByName(String name) {
        return userServiceClient.getUserByName(name).getId();
    }

    public List<PlannerEntity> getUsersPlanners(Long userId) {
        List<PlannerEntity> planner = plannerRepository.findByUserId(userId);
        for (PlannerEntity item : planner) {
            DiaryDTO diary = diaryServiceClient.getDiaryById(item.getDiaryId());
            item.setDiary(diary);

            UserDTO user = userServiceClient.getUserById(item.getUserId());
            item.setUser(user);
        }
        return planner;
    }
}