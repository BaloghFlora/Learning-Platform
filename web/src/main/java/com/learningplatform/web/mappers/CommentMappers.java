package com.learningplatform.web.mappers;

import com.learningplatform.web.DTO.CommentDTO;
import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.models.Comment;
import com.learningplatform.web.models.Lecture;

public class CommentMappers {
    public static Comment mapToComment(CommentDTO commentDTO) {
        return Comment.builder()
                .commentId(commentDTO.getCommentId())
                .text(commentDTO.getText())
                .writtenBy(commentDTO.getWrittenBy())
                .course(commentDTO.getCourse())
                .build();
    }

    public static CommentDTO mapToCommentDto(Comment comment) {
        return CommentDTO.builder()
                .commentId(comment.getCommentId())
                .text(comment.getText())
                .writtenBy(comment.getWrittenBy())
                .course(comment.getCourse())
                .build();
    }
}
