package com.learningplatform.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long commentId;
    private String text;
    @ManyToOne
    @JoinColumn(name = "written_by" , nullable = false)
    private UserEntity writtenBy;
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;
}
