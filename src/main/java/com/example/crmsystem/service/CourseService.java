package com.example.crmsystem.service;

import com.example.crmsystem.dto.CourseDTO;
import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO dto);
    List<CourseDTO> getAllCourses();
    CourseDTO getCourseById(Long id);
    CourseDTO updateCourse(Long id, CourseDTO dto);
    boolean deleteCourse(Long id);
}
