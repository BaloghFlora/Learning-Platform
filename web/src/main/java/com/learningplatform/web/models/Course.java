package com.learningplatform.web.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long ID;
    private String title;
    private String photourl;
    private String content;
    @CreationTimestamp
    private java.time.LocalDateTime createdOn;
    @UpdateTimestamp
    private java.time.LocalDateTime updatedOn;
    @ManyToOne
    @JoinColumn(name = "created_by" , nullable = false)
    private UserEntity createdBy;


    public void setID(long ID) {
        this.ID = ID;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = LocalDateTime.now();
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn =LocalDateTime.now();
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return LocalDateTime.now();
    }



    @OneToMany(mappedBy = "course", cascade  = CascadeType.REMOVE)
    private List<Lecture> lectures = new ArrayList<>();

}
