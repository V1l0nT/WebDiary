package org.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class DiaryDTO {
    private Long id;
    private String name;
    private String date;
    private String record;
}