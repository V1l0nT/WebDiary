package org.example.diaryservice.controller;

import lombok.AllArgsConstructor;
import org.example.diaryservice.entity.DiaryEntity;
import org.example.diaryservice.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Controller
@AllArgsConstructor
public class PagesController {
    private final DiaryService diaryService;

    @GetMapping("/life")
    public String menu(Model model, @RequestHeader("X-UserName") String name){
        List<DiaryEntity> diaries = diaryService.readAll();
        model.addAttribute("name", name);
        model.addAttribute("diaries", diaries);
        return "life";
    }
}
