package com.inovabook.web.controller;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.model.Course;
import com.inovabook.web.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model){
        List<CourseDto> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course-list";
    }

    @GetMapping("/courses/new")
    public String createCourse(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course-create";
    }

    /*@PostMapping("/courses/new")
    public String saveCourse(@ModelAttribute("course") Course course){
        courseService.saveCourse(course);
        return "redirect:/courses";
    }*/

    @PostMapping("/courses/new")
    public String saveCourse(@ModelAttribute("course") Course course, @RequestParam("thumbnailFile")
    MultipartFile file, Model model) throws IOException {
        courseService.saveCourse(course, file);
        model.addAttribute("messange", "Curso salvo com sucesso!");
        return "redirect:/courses";
    }



}