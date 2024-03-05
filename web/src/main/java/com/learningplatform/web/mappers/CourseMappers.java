package com.learningplatform.web.mappers;

import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.models.Course;

import java.util.List;
import java.util.stream.Collectors;

import static com.learningplatform.web.mappers.LectureMappers.mapToLectureDto;

public class CourseMappers {

    public static Course mapToCourse(CourseDTO course) {
        Course courseDTO = Course.builder()
                .ID(course.getID())
                .title(course.getTitle())
                .photourl(course.getPhotourl())
                .content(course.getContent())
                .createdBy(course.getCreatedBy())
                .createdOn(course.getCreatedOn())
                .updatedOn(course.getUpdatedOn())
                .build();
        return courseDTO;
    }


    public static CourseDTO mapToCourseDTO(Course course) {
        CourseDTO courseDTO = CourseDTO.builder()
                .ID(course.getID())
                .title(course.getTitle())
                .content(course.getContent())
                .photourl(course.getPhotourl())
                .createdBy(course.getCreatedBy())
                .createdOn(course.getCreatedOn())
                .updatedOn(course.getUpdatedOn())
                .lectures(course.getLectures().stream().map((lecture)->mapToLectureDto(lecture)).collect(Collectors.toList()))
                .build();
        return courseDTO;
    }
}
