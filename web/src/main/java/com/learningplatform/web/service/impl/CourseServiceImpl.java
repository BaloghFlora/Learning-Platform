package com.learningplatform.web.service.impl;

import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.UserEntity;
import com.learningplatform.web.repository.CourseRepository;
import com.learningplatform.web.repository.UserRepository;
import com.learningplatform.web.security.SecurityUtil;
import com.learningplatform.web.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.learningplatform.web.mappers.CourseMappers.mapToCourse;
import static com.learningplatform.web.mappers.CourseMappers.mapToCourseDTO;
@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course)->mapToCourseDTO(course)).collect(Collectors.toList());
    }

    @Override
    public Course saveCourse(@Valid CourseDTO courseDTO) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByUsername(username);
        Course course = mapToCourse(courseDTO);
        course.setCreatedBy(user);
        return courseRepository.save(course);
    }




    @Override
    public CourseDTO findCourseById(long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return mapToCourseDTO(course);
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {

        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByUsername(username);
        Course course = mapToCourse(courseDTO);
        course.setCreatedBy(user);
        courseRepository.save(course);



    }

    @Override
    public void delete(Long courseID) {

        courseRepository.deleteById(courseID);
    }

    @Override
    public List<CourseDTO> searchCourse(String query) {
        List<Course> courses = courseRepository.searchCourse(query);
        return courses.stream().map(course ->mapToCourseDTO(course)).collect(Collectors.toList());
    }


}
