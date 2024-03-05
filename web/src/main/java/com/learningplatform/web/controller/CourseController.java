package com.learningplatform.web.controller;

import com.learningplatform.web.DTO.CommentDTO;
import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.models.Comment;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.UserEntity;
import com.learningplatform.web.security.SecurityUtil;
import com.learningplatform.web.service.CommentService;
import com.learningplatform.web.service.CourseService;
import com.learningplatform.web.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class CourseController {
    private CourseService courseService;
    private UserService userService;
    private CommentService commentService;
    @Autowired
    public CourseController(CourseService courseService, UserService userService, CommentService commentService) {
        this.courseService = courseService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model){
        UserEntity user = new UserEntity();
        List<CourseDTO> courses = courseService.findAllCourses();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("courses", courses);
        return "courses-list";

    }
    @GetMapping("/courses/{courseId}")
    public String courseDetail(@PathVariable("courseId") Long courseId, Model model){
        CourseDTO courseDTO = courseService.findCourseById(courseId);
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        List<CommentDTO> comments = new ArrayList<>();
        comments = commentService.FindAllCommentsById(courseId);
        model.addAttribute("user", user);
        model.addAttribute("comments", comments);
        model.addAttribute("course", courseDTO);
        return "courses-detail";
    }
    @GetMapping("/courses/new")
    public String createCourseForm(Model model)
    {
        Course course = new Course();
        model.addAttribute("course", course);
        return "courses-create";
    }
    @GetMapping("/courses/search")
    public String searchCourse(@RequestParam(value = "query") String query,Model model){

        UserEntity user = new UserEntity();
        List<CourseDTO> courses = courseService.searchCourse(query);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("courses", courses);
        return "courses-list";

    }
    @PostMapping("/courses/new")
    public String saveCourse(@Valid @ModelAttribute("course")CourseDTO courseDTO, @NotNull BindingResult result, Model model) {
    if(result.hasErrors()){

        model.addAttribute("course", courseDTO);

        return "courses-create";
        }

    courseService.saveCourse(courseDTO);
    return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/edit")
    public String editCourseForm(@PathVariable("courseId") Long courseId, Model model){
        CourseDTO course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
        return "courses-edit";
    }
    @PostMapping("/courses/{courseId}/edit")
    public String updateCourse(@PathVariable("courseId") Long courseId, @Valid @ModelAttribute("course") CourseDTO course,
                               @NotNull BindingResult result){
        if(result.hasErrors())
        {
            return "courses-edit";
        }
        course.setID(courseId);
        courseService.updateCourse(course);
        return "redirect:/courses";
    }
    @GetMapping("/courses/{courseID}/delete")
    public String deleteCourse(@PathVariable("courseID") Long courseID){
        courseService.delete(courseID);
        return"redirect:/courses";
    }
}



