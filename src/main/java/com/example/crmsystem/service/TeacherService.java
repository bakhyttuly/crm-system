package com.example.crmsystem.service;

import com.example.crmsystem.dto.TeacherDTO;
import java.util.List;

public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);
    List<TeacherDTO> getAllTeachers();
    TeacherDTO getTeacherById(Long id);
    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);
    boolean deleteTeacher(Long id);
}