package com.inovabook.web.controller;

import com.inovabook.web.dto.CourseDto;
import com.inovabook.web.model.Course;
import com.inovabook.web.model.User;
import com.inovabook.web.repository.CourseRepository;
import com.inovabook.web.repository.EnrollmentRepository;
import com.inovabook.web.service.CourseService;
import com.inovabook.web.service.EnrollService;
import com.inovabook.web.service.LessonService;
import com.inovabook.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CourseController {

    private CourseService courseService;
    private UserService userService;
    private LessonService lessonService;
    private CourseRepository courseRepository;
    private EnrollService enrollService;
    private EnrollmentRepository enrollmentRepository;

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

    @GetMapping("/courses/search")
    public String searchCourse(Model model,
                               @RequestParam(value="query") String query){
        List<CourseDto> courses = courseService.searchCourse(query);
        model.addAttribute("courses", courses);
        return "course-list";
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

    @GetMapping("/courses/{id}")
    public String courseView(@PathVariable("id") Long id,
                                 Model model,
                             @AuthenticationPrincipal User user){
            if (user != null) {
                return "redirect:/login";
            }
        CourseDto courseDto = courseService.findById(id);
        model.addAttribute("course", courseDto);
        model.addAttribute("progress", lessonService.getLessonProgressForCourse(user, id));

        boolean enrolled = enrollmentRepository.existsByUserAndCourse(user, courseRepository.findById(id).orElse(null));
        model.addAttribute("enrolled", enrolled);
        return "course-view";
    }

    @PostMapping("/courses/{id}/edit")
    public String updateCourse(@PathVariable("id") Long id,
                               @Valid @ModelAttribute("course") CourseDto course,
                               BindingResult result,
                               @RequestParam("thumbnailFile") MultipartFile file,
                               Model model) {
        if(result.hasErrors()){
            model.addAttribute("course", course);
            System.out.println("Erro de validação: " + result.getAllErrors());
            return "course-edit";
        }
        course.setId(id);
        courseService.updateCourse(course, file);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/enroll/{id}")
    public String enroll(@PathVariable Long courseId,
                         RedirectAttributes ra,
                         @AuthenticationPrincipal User user) {  // Auto-injected!
            if(user == null){
                return "redirect:/login";
            }
        enrollService.subscribeUserToCourse(user, courseId);
        ra.addFlashAttribute("message", "Enrolled successfully!");
        return "redirect:/courses";
    }

    @PostMapping("/courses/{courseId}/lesson/{lessonId}/seen")
    public String markLessonSeen(
            @PathVariable Long courseId,
            @PathVariable Long lessonId,
            RedirectAttributes redirectAttrs,
            @AuthenticationPrincipal User user) {

        lessonService.markLessonAsSeen(user, lessonId);
        redirectAttrs.addFlashAttribute("message", "Lesson marked as seen!");
        return "redirect:/courses/" + courseId;
    }
}