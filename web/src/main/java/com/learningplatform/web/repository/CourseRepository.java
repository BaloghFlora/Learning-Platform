package com.learningplatform.web.repository;

import com.learningplatform.web.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByTitle (String url);
    @Query("SELECT c from Course c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Course> searchCourse (String query);
}
