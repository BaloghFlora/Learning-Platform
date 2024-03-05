package com.learningplatform.web.DTO;



import com.learningplatform.web.models.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Data
@Builder
public class CourseDTO {
    private Long ID;
    @NotEmpty(message = "Course title should not be empty!")
    private String title;
    private String photourl;
    @NotEmpty(message = "Course content should not be empty!")
    private String content;
    private java.time.LocalDateTime createdOn;
    private java.time.LocalDateTime updatedOn;
    private UserEntity createdBy;
    private List<LectureDTO> lectures;
}
