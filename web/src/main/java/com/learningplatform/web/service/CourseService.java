package com.learningplatform.web.service;

import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.models.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService {
    List<CourseDTO> findAllCourses();

    Course saveCourse(CourseDTO courseDTO);

    CourseDTO findCourseById(long courseId);

    void updateCourse(CourseDTO courseDTO);

    void delete(Long courseID);

    List<CourseDTO> searchCourse(String query);
}
