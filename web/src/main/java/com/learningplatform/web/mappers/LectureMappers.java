package com.learningplatform.web.mappers;

import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.models.Lecture;

public class LectureMappers {

    public static Lecture mapToLecture(LectureDTO lectureDTO) {
        return Lecture.builder()
                .ID(lectureDTO.getID())
                .name(lectureDTO.getName())
                .createdOn(lectureDTO.getCreatedOn())
                .updatedOn(lectureDTO.getUpdatedOn())
                .materialLink(lectureDTO.getMaterialLink())
                .content(lectureDTO.getContent())
                .course(lectureDTO.getCourse())
                .build();
    }

    public static LectureDTO mapToLectureDto(Lecture lecture) {
        return LectureDTO.builder()
                .ID(lecture.getID())
                .name(lecture.getName())
                .createdOn(lecture.getCreatedOn())
                .updatedOn(lecture.getUpdatedOn())
                .materialLink(lecture.getMaterialLink())
                .content(lecture.getContent())
                .course(lecture.getCourse())
                .build();
    }
}
