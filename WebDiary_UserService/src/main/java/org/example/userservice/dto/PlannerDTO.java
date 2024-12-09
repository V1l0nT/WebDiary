package org.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlannerDTO {
    private String name;
    private String description;
    private boolean status;
}