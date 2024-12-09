package org.example.plannerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.plannerservice.entity.PlannerEntity;

@Data
@AllArgsConstructor
public class PlannerWithDiaryDTO {
    private PlannerEntity plannerEntity;
    private DiaryDTO diaryDTO;
}