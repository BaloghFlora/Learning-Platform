package com.learningplatform.web.service;

import com.learningplatform.web.DTO.CommentDTO;
import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.DTO.LectureDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    void createComment(Long courseId, CommentDTO commentDTO);
    public List<CommentDTO> FindAllCommentsById(Long query);

    void deleteComment(Long commentId);

    CommentDTO findByCommentId(Long commentId);

    void updateComment(CommentDTO commentDTO1);
}
