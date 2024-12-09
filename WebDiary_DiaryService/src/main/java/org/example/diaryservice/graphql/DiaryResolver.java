package org.example.diaryservice.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.diaryservice.dto.DiaryDTO;
import org.example.diaryservice.entity.DiaryEntity;
import org.example.diaryservice.service.DiaryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class DiaryResolver {
    private final DiaryService diaryService;

    @QueryMapping
    public List<DiaryEntity> getAllDiaries() {
        return diaryService.readAll();
    }

    @QueryMapping
    public DiaryEntity getDiaryById(@Argument Long id) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DiaryEntity diary = diaryService.findById(id).orElse(null);
        String episodes = objectMapper.writeValueAsString(diary.getEpisodes());
        diary.setEpisodeJson(episodes);
        return diary;
    }

    @MutationMapping
    public DiaryEntity createDiary(@Argument DiaryDTO input) {
        return diaryService.create(input);
    }

    @MutationMapping
    public Boolean deleteDiary(@Argument Long id) {
        diaryService.delete(id);
        return true;
    }
}