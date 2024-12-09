package org.example.plannerservice.graphql;

import lombok.AllArgsConstructor;
import org.example.plannerservice.entity.PlannerEntity;
import org.example.plannerservice.repository.PlannerRepository;
import org.example.plannerservice.service.PlannerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PlannerResolver {
    private final PlannerService plannerService;
    private final PlannerRepository plannerRepository;

    @QueryMapping
    public List<PlannerEntity> getUsersPlanners(@Argument Long userId) {
        return plannerService.getUsersPlanners(userId);
    }

    @MutationMapping
    public PlannerEntity addDiaryToPlanners(@Argument Long diaryId, @Argument String username) {
        Long userId = plannerService.getUserIdByName(username);
        return plannerService.addToListByUserID(userId, diaryId);
    }

    @MutationMapping
    public PlannerEntity changeStatus(@Argument Long id, @Argument String status) {
        Optional<PlannerEntity> plannerEntity = plannerService.findPlannerById(id);
        if (plannerEntity.isEmpty()) {
            return null;
        }
        List<String> statuses = List.of("Watching", "Plan to watch", "Watched");
        if (!statuses.contains(status)) {
            return null;
        }
        PlannerEntity planner = plannerEntity.get();
        plannerRepository.save(planner);
        return planner;
    }

    @MutationMapping
    public PlannerEntity changeRating(@Argument Long id, @Argument Double rating) {
        Optional<PlannerEntity> plannerEntity = plannerService.findPlannerById(id);
        if (plannerEntity.isEmpty()) {
            return null;
        }
        if (rating > 10.0) {
            return null;
        }
        PlannerEntity planner = plannerEntity.get();
        plannerRepository.save(planner);
        return planner;
    }

    @MutationMapping
    public PlannerEntity incrementEpisodes(@Argument Long id) {
        Optional<PlannerEntity> plannerEntity = plannerService.findPlannerById(id);
        if (plannerEntity.isEmpty()) {
            return null;
        }
        PlannerEntity planner = plannerEntity.get();
        return planner;
    }
}