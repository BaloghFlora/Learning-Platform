package com.learningplatform.web.service.impl;

import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.Lecture;
import com.learningplatform.web.repository.CourseRepository;
import com.learningplatform.web.repository.LectureRepository;
import com.learningplatform.web.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.learningplatform.web.mappers.LectureMappers.mapToLecture;
import static com.learningplatform.web.mappers.LectureMappers.mapToLectureDto;

@Service
public class LectureServiceImpl implements LectureService {
    private LectureRepository lectureRepository;
    private CourseRepository courseRepository;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository, CourseRepository courseRepository) {
        this.lectureRepository = lectureRepository;
        this.courseRepository = courseRepository;
    }



    @Override
    public void createLecture(Long courseId, LectureDTO lectureDTO) {
        Course course = courseRepository.findById(courseId).get();
        Lecture lecture = mapToLecture(lectureDTO);
       // lecture.setFilePath();
        lecture.setCourse(course);
        lecture.setMaterialLink(lectureDTO.getMaterialLink());
        lectureRepository.save(lecture);
    }

    @Override
    public void updateEvent(LectureDTO lectureDTO) {
        Lecture lecture = mapToLecture(lectureDTO);
        lectureRepository.save(lecture);
    }

    @Override
    public void deleteLecture(Long lectureId) {
        lectureRepository.deleteById(lectureId);
    }

    @Override
    public LectureDTO findByLectureId(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).get();
        return mapToLectureDto(lecture);
    }

    @Override
    public void updateLecture(LectureDTO lectureDTO) {
        Lecture lecture = mapToLecture(lectureDTO);
        lectureRepository.save(lecture);
    }

}
