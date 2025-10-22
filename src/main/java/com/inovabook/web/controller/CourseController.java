package com.inovabook.web.controller;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.model.Course;
import com.inovabook.web.service.CourseService;
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
    public String createCourseForm(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "course-create";
    }

    @PostMapping("/courses/new")
    public String saveCourse(@Valid @ModelAttribute("course") CourseDto courseDto,
                             @RequestParam("thumbnailFile") MultipartFile file,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", courseDto);
            return "course-create";
        }
        courseService.saveCourse(courseDto, file);
        model.addAttribute("message", "Curso salvo com sucesso!");
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}/edit")
    public String editCourseForm(@PathVariable("id") Long id,
                                 Model model){
        CourseDto course = courseService.findById(id);
        model.addAttribute("course", course);
        return "course-edit";
    }

    @PostMapping("/courses/{id}/edit")
    public String updateCourse(@PathVariable("id") Long id,
                               @Valid @ModelAttribute("course") CourseDto course,
                               BindingResult result,
                               @RequestParam("thumbnailFile") MultipartFile file,
                               Model model) {
        if(result.hasErrors()){
            System.out.println("Erro de validação: " + result.getAllErrors());
            return "course-edit";
        }
        course.setId(id);
        courseService.updateCourse(course, file);
        return "redirect:/courses";
    }
}