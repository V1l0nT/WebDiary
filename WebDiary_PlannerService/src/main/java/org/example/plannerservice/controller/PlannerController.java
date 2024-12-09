package org.example.plannerservice.controller;

import lombok.AllArgsConstructor;
import org.example.plannerservice.entity.PlannerEntity;
import org.example.plannerservice.service.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PlannerController {
    private final PlannerService plannerService;

    @RequestMapping(value = "/diary", method = RequestMethod.POST)
    public ResponseEntity<PlannerEntity> create(@RequestParam("diary") Long diaryId, @RequestHeader("X-UserName") String name) {
        return new ResponseEntity<>(plannerService.addToList(name, diaryId), HttpStatus.OK);
    }
}