package com.example.crmsystem.mapper;

import com.example.crmsystem.dto.StudentDTO;
import com.example.crmsystem.entity.Course;
import com.example.crmsystem.entity.Student;
import com.example.crmsystem.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "teachers", target = "teacherIds")
    StudentDTO toDto(Student student);

    @Mapping(source = "courseId", target = "course.id")
    @Mapping(target = "teachers", ignore = true)
    Student toEntity(StudentDTO dto);

    List<StudentDTO> toDtoList(List<Student> students);

    default Long teacherToLong(Teacher teacher) {
        if (teacher == null) return null;
        return teacher.getId();
    }

    default Course longToCourse(Long courseId) {
        if (courseId == null) return null;
        Course course = new Course();
        course.setId(courseId);
        return course;
    }
}