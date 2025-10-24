package com.inovabook.web.controller;

import com.inovabook.web.dto.LessonDto;
import com.inovabook.web.model.Lesson;
import com.inovabook.web.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/lesson/{id}/new")
    public String createLesson(@PathVariable("id") Long id,
                               Model model) {
        Lesson lesson = new Lesson();
        model.addAttribute("id", id);
        model.addAttribute("lesson", lesson);
        return "lesson-create";
    }

    @PostMapping("/lesson/{id}/new")
    public String saveLesson(@PathVariable("id") Long id,
                            @Valid @ModelAttribute("lesson") LessonDto lessonDto,
                            @RequestParam("videoFile") MultipartFile file,
                            BindingResult result,
                            Model model) {
       if (result.hasErrors()) {
            model.addAttribute("lesson", lessonDto);
            return "lesson-create";
       }
        lessonService.createLesson(id, lessonDto, file);
        model.addAttribute("message", "Lição salva com sucesso!");
        return "redirect:/courses/" + id;
    }

}


