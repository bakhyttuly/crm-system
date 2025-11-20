package com.example.crmsystem.mapper;

import com.example.crmsystem.dto.CourseDTO;
import com.example.crmsystem.entity.Course;
import com.example.crmsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(source = "students", target = "studentIds")
    CourseDTO toDto(Course course);

    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDTO dto);

    List<CourseDTO> toDtoList(List<Course> courses);

    default Long studentToLong(Student student) {
        return student.getId();
    }
}