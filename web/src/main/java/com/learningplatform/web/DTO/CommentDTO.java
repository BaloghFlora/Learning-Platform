package com.learningplatform.web.DTO;

import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long commentId;
    @NotEmpty(message = "Cannot display empty comment!")
    private String text;
    private UserEntity writtenBy;

    private Course course;
}
