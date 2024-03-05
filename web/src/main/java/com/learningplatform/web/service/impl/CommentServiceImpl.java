package com.learningplatform.web.service.impl;

import com.learningplatform.web.DTO.CommentDTO;
import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.models.Comment;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.Lecture;
import com.learningplatform.web.models.UserEntity;
import com.learningplatform.web.repository.CommentRepository;
import com.learningplatform.web.repository.CourseRepository;
import com.learningplatform.web.repository.LectureRepository;
import com.learningplatform.web.repository.UserRepository;
import com.learningplatform.web.security.SecurityUtil;
import com.learningplatform.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.learningplatform.web.mappers.CommentMappers.mapToComment;
import static com.learningplatform.web.mappers.CommentMappers.mapToCommentDto;
import static com.learningplatform.web.mappers.CourseMappers.mapToCourse;
import static com.learningplatform.web.mappers.LectureMappers.mapToLecture;
import static com.learningplatform.web.mappers.LectureMappers.mapToLectureDto;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(Long courseId, CommentDTO commentDTO) {
        Course course = courseRepository.findById(courseId).get();
        Comment comment  = mapToComment(commentDTO);
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        comment.setWrittenBy(user);
        comment.setCourse(course);
        comment.setText(commentDTO.getText());
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> FindAllCommentsById(Long query) {
        List <Comment> comments = commentRepository.searchComments(query);
        return comments.stream().map(comment ->mapToCommentDto(comment)).collect(Collectors.toList());

    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDTO findByCommentId(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        return mapToCommentDto(comment);

    }

    @Override
    public void updateComment(CommentDTO commentDTO1) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByUsername(username);
        Comment comment = mapToComment(commentDTO1);
        comment.setWrittenBy(user);
        commentRepository.save(comment);
    }


}
