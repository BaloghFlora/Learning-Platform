package com.learningplatform.web.DTO;


import com.learningplatform.web.models.Course;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    private Long ID;
    @NotEmpty(message = "Lecture name should not be empty!")
    private String name;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
    private LocalDateTime deadline;
    @NotEmpty(message = "Lecture material should not be empty!")
    private String materialLink;
    private Course course;
    private String content;

    public void setMaterialLink( String materialLink) {
        this.materialLink =  materialLink;
    }
}
