package org.example.diaryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EpisodeDTO {
    private String season;
    private int count;
}
