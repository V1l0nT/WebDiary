package org.example.plannerservice.repository;

import org.example.plannerservice.entity.PlannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannerRepository extends JpaRepository<PlannerEntity, Long> {
    PlannerEntity findByUserIdAndDiaryId(Long userId, Long diaryId);

    List<PlannerEntity> findByUserId(Long userId);
}