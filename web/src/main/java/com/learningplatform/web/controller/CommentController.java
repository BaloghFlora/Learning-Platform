package com.learningplatform.web.controller;

import com.learningplatform.web.DTO.CommentDTO;
import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.models.Comment;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.UserEntity;
import com.learningplatform.web.repository.CommentRepository;
import com.learningplatform.web.repository.LectureRepository;
import com.learningplatform.web.security.SecurityUtil;
import com.learningplatform.web.service.CommentService;
import com.learningplatform.web.service.CourseService;
import com.learningplatform.web.service.LectureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.learningplatform.web.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@SpringBootApplication
public class CommentController {
    private CommentService commentService ;
    private CommentRepository commentRepository;
    private CourseService courseService;
    private UserService userService;

    public CommentController(CommentService commentService, CommentRepository commentRepository, CourseService courseService, UserService userService) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping("/courses/{courseId}/comments")
    public String saveComment(@PathVariable("courseId") Long courseId, @ModelAttribute("comment") CommentDTO commentDTO, Model model) {
        commentService.createComment(courseId, commentDTO);
        return "redirect:/courses/"+ courseId;
    }

    @PostMapping("/comments/{commentId}/delete")
    public String deleteComment( @PathVariable Long commentId) {
        // Implement logic to delete the comment (use a service or repository)
        CommentDTO commentDTO = commentService.findByCommentId(commentId);
        Course course = commentDTO.getCourse();
        commentService.deleteComment(commentId);
        return "redirect:/courses/"+course.getID();
    }

    @GetMapping("/comments/{commentId}/update")
    public String updateCommentForm(@PathVariable Long commentId, Model model) {

        CommentDTO comment = commentService.findByCommentId(commentId);
        model.addAttribute("comment", comment);
        return "comments-update";

    }

    @PostMapping("/comments/{commentId}/update")
    public String updateComment( @PathVariable Long commentId, @Valid @ModelAttribute("comment") CommentDTO commentDTO,
                                 BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("comment", commentDTO);
            return "comments-update";
        }
        CommentDTO commentDTO1 = commentService.findByCommentId(commentId);
        commentDTO.setCommentId(commentId);
        commentDTO.setCourse(commentDTO1.getCourse());
        commentService.updateComment(commentDTO);
        return "redirect:/courses/" + commentDTO1.getCourse().getID();
    }


}
