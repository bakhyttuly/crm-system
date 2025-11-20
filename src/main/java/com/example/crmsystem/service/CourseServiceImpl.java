package com.example.crmsystem.service;

import com.example.crmsystem.dto.CourseDTO;
import com.example.crmsystem.entity.Course;
import com.example.crmsystem.repository.CourseRepository;
import com.example.crmsystem.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course createdCourse = courseRepository.save(course);
        return courseMapper.toDto(createdCourse);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDtoList(courses);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if(Objects.isNull(course)){
            return null;
        }
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        CourseDTO checkCourse = getCourseById(id);
        if(Objects.isNull(checkCourse)){
            return null;
        }

        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) return null;

        Course courseUpdate = courseMapper.toEntity(courseDTO);

        existingCourse.setDescription(courseUpdate.getDescription());

        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDto(updatedCourse);
    }

    @Override
    public boolean deleteCourse(Long id) {
        CourseDTO checkCourse = getCourseById(id);
        if(Objects.isNull(checkCourse)){
            return false;
        }
        courseRepository.deleteById(id);
        return true;
    }
}