package org.example.diaryservice.controller;

import lombok.AllArgsConstructor;
import org.example.diaryservice.dto.DiaryDTO;
import org.example.diaryservice.entity.DiaryEntity;
import org.example.diaryservice.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @RequestMapping(value = "/diary", method = RequestMethod.GET)
    public ResponseEntity<List<DiaryEntity>> readAll() {
        return new ResponseEntity<>(diaryService.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/diary/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<DiaryEntity>> findByID(@PathVariable Long id) {
        return new ResponseEntity<>(diaryService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/diary", method = RequestMethod.POST)
    public ResponseEntity<DiaryEntity> create(@RequestBody DiaryDTO dto) {
        return new ResponseEntity<>(diaryService.create(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/diary", method = RequestMethod.PUT)
    public ResponseEntity<DiaryEntity> update(@RequestBody DiaryEntity diary) {
        return new ResponseEntity<>(diaryService.update(diary), HttpStatus.OK);
    }

    @DeleteMapping("/diary/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        diaryService.delete(id);
        return HttpStatus.OK;
    }
}