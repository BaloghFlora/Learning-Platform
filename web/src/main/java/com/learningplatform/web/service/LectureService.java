package com.learningplatform.web.service;

import com.learningplatform.web.DTO.LectureDTO;

public interface LectureService {
    void createLecture(Long courseId, LectureDTO lectureDTO);
    void updateEvent(LectureDTO lectureDTO);

    LectureDTO findByLectureId(Long lectureId);

    void updateLecture(LectureDTO lectureDTO);

    void deleteLecture(Long lectureId);
}
