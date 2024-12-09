package org.example.diaryservice.service;

import lombok.AllArgsConstructor;
import org.example.diaryservice.entity.DiaryEntity;
import org.example.diaryservice.dto.DiaryDTO;
import org.example.diaryservice.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryEntity create(DiaryDTO dto){
        Map<String, Integer> episodes;
        if (!dto.isSeries()) {
            episodes = Map.of("diary", 1);
        } else {
            episodes = dto.getEpisodes();
        }
        DiaryEntity diary = DiaryEntity.builder()
                .name(dto.getName())
                .releaseDate(dto.getReleaseDate())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .series(dto.isSeries())
                .episodes(episodes)
                .img(dto.getImg())
                .build();
        return diaryRepository.save(diary);
    }
    public List<DiaryEntity> readAll() {
        return diaryRepository.findAll();
    }

    public Optional<DiaryEntity> findById(Long id){return diaryRepository.findById(id);}

    public DiaryEntity update(DiaryEntity diary) {
        DiaryEntity existingDiary = diaryRepository.findById(diary.getId())
                .orElseThrow(() -> new IllegalArgumentException("Дневник с айди: " + diary.getId() + " - не найден"));

        existingDiary.setName(diary.getName());
        existingDiary.setReleaseDate(diary.getReleaseDate());
        existingDiary.setDescription(diary.getDescription());
        existingDiary.setRating(diary.getRating());
        existingDiary.setSeries(diary.isSeries());

        if (!diary.isSeries()) {
            existingDiary.setEpisodes(Map.of("diary", 1));
        } else {
            existingDiary.setEpisodes(diary.getEpisodes());
        }

        existingDiary.setImg(diary.getImg());

        return diaryRepository.save(existingDiary);
    }


    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }
}