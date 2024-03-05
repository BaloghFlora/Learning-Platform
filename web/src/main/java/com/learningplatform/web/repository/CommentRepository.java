package com.learningplatform.web.repository;

import com.learningplatform.web.models.Comment;
import com.learningplatform.web.models.Course;
import com.learningplatform.web.models.Lecture;
import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT c from Comment c  WHERE c.course.ID = :query")
    List<Comment> searchComments(@Param("query")Long query);
}
