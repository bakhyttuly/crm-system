package com.example.crmsystem.mapper;

import com.example.crmsystem.dto.TeacherDTO;
import com.example.crmsystem.entity.Teacher;
import com.example.crmsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(source = "students", target = "studentIds")
    TeacherDTO toDto(Teacher teacher);

    @Mapping(target = "students", ignore = true)
    Teacher toEntity(TeacherDTO dto);

    List<TeacherDTO> toDtoList(List<Teacher> teachers);

    default Long studentToLong(Student student) {
        return student.getId();
    }
}