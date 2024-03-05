package com.learningplatform.web.controller;

import com.learningplatform.web.DTO.CourseDTO;
import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.Lecture;
import com.learningplatform.web.repository.LectureRepository;
import com.learningplatform.web.service.CourseService;
import com.learningplatform.web.service.LectureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@SpringBootApplication
public class LectureController {
    private LectureService lectureService;
    private LectureRepository lectureRepository;
    private CourseService courseService;

    @Autowired
    public LectureController(LectureService lectureService) {
        //this.userService = userService;
        this.lectureService = lectureService;
    }

//    @GetMapping("/lectures")
//    public String eventList(Model model) {
////        UserEntity user = new UserEntity();
////        List<EventDto> events = eventService.findAllEvents();
////        String username = SecurityUtil.getSessionUser();
////        if(username != null) {
////            user = userService.findByUsername(username);
////            model.addAttribute("user", user);
////        }
////        model.addAttribute("user", user);
//        model.addAttribute("events", events);
//        return "events-list";
//    }

//    @GetMapping("/lectures/{lectureId}")
//    public String viewEvent(@PathVariable("eventId")Long eventId, Model model) {
////        UserEntity user = new UserEntity();
////        EventDto eventDto = eventService.findByEventId(eventId);
//        String username = SecurityUtil.getSessionUser();
//        if(username != null) {
//            user = userService.findByUsername(username);
//            model.addAttribute("user", user);
//        }
//        model.addAttribute("club", eventDto.getClub());
//        model.addAttribute("user", user);
//        model.addAttribute("event", eventDto);
//        return "events-detail";
//    }
//
    @GetMapping("/lectures/{courseId}/new")
    public String createLectureForm(@PathVariable("courseId") Long courseId, Model model) {
        Lecture lecture  = new Lecture();
        model.addAttribute("courseId", courseId);
        model.addAttribute("lecture", lecture);
        return "lectures-create";
    }
    @PostMapping("courses/{courseId}")
    public String openFile(@PathVariable("courseId") Long courseId, @ModelAttribute("lecture") Lecture lecture, BindingResult result, Model model){
        return "redirect:"+lecture.getMaterialLink();
    }

    @GetMapping("/lectures/{lectureId}/edit")
    public String editLectureForm(@PathVariable("lectureId") Long lectureId, Model model) {
        LectureDTO lecture = lectureService.findByLectureId(lectureId);
        model.addAttribute("lecture", lecture);
        return "lectures-edit";
    }

    @PostMapping("/lectures/{courseId}/new")
    public String createLecture(@PathVariable("courseId") Long courseId, @ModelAttribute("lecture") LectureDTO lectureDTO,
                              BindingResult result,
                              Model model) {
        lectureService.createLecture(courseId, lectureDTO);
        return "redirect:/courses/"+ courseId;
    }

    @PostMapping("/lectures/{lectureid}/edit")
    public String updateLecture(@PathVariable("lectureid") Long lectureId,
                              @Valid @ModelAttribute("lecture") LectureDTO lectureDTO,
                              BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("lecture", lectureDTO);
            return "lectures-edit";
        }
        LectureDTO lectureDto = lectureService.findByLectureId(lectureId);
        lectureDTO.setID(lectureId);
        lectureDTO.setCourse(lectureDto.getCourse());
        lectureService.updateLecture(lectureDTO);
        return "redirect:/courses/" + lectureDto.getCourse().getID();
    }
//
    @GetMapping("/lectures/{lectureId}/delete")
    public String deleteLecture(@PathVariable("lectureId") long lectureId) {
        LectureDTO lectureDTO = lectureService.findByLectureId(lectureId);
        Course course = lectureDTO.getCourse();
        lectureService.deleteLecture(lectureId);
        return "redirect:/courses/" +course.getID() ;
    }
}
