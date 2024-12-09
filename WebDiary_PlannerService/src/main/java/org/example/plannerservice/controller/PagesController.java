package org.example.plannerservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.plannerservice.entity.PlannerEntity;
import org.example.plannerservice.repository.PlannerRepository;
import org.example.plannerservice.service.PlannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PagesController {
    private final PlannerService plannerService;
    private final PlannerRepository plannerRepository;

    @GetMapping("/diary")
    public String plannerPage(Model model, HttpServletRequest request) {
        String name = request.getHeader("X-UserName");
        Long id = plannerService.getUserIdByName(name);
        List<PlannerEntity> planner = plannerService.getUsersPlanners(id);
        Map<String, List<PlannerEntity>> sortedByStatus = planner.stream()
                .collect(Collectors.groupingBy(PlannerEntity::getPlannerStatus));

        model.addAttribute("username", name);
        model.addAttribute("allStatuses", List.of("Unread", "Read"));
        model.addAttribute("planner", sortedByStatus);
        return "planner";
    }

    @PostMapping("/diary/{id}/status")
    public ResponseEntity<String> updatePlannerStatus(@PathVariable Long id, @RequestParam("status") String status) {
        Optional<PlannerEntity> plannerOptional = plannerRepository.findById(id);

        if (plannerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PlannerEntity planner = plannerOptional.get();
        planner.setRead(status.equals("Read"));
        plannerRepository.save(planner);

        return ResponseEntity.ok("Planner status updated successfully");
    }

    @PostMapping("/diary/{id}/rating")
    public ResponseEntity<String> updateUserRating(@PathVariable Long id, @RequestParam("rating") double rating) {
        Optional<PlannerEntity> plannerOptional = plannerRepository.findById(id);

        if (plannerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PlannerEntity planner = plannerOptional.get();
        // Здесь можно добавить логику для обновления рейтинга уведомления, если это необходимо.
        plannerRepository.save(planner);

        return ResponseEntity.ok("User rating updated successfully");
    }

    @PostMapping("/diary/{id}/increment")
    public ResponseEntity<String> incrementWatchedEpisodes(@PathVariable Long id) {
        Optional<PlannerEntity> plannerOptional = plannerRepository.findById(id);

        if (plannerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PlannerEntity planner = plannerOptional.get();

        return ResponseEntity.ok("Watched episodes incremented successfully");
    }
}