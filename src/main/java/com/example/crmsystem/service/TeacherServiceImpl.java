package com.example.crmsystem.service;

import com.example.crmsystem.dto.TeacherDTO;
import com.example.crmsystem.entity.Teacher;
import com.example.crmsystem.repository.TeacherRepository;
import com.example.crmsystem.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        Teacher createdTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(createdTeacher);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teacherMapper.toDtoList(teachers);
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if(Objects.isNull(teacher)){
            return null;
        }
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        TeacherDTO checkTeacher = getTeacherById(id);
        if(Objects.isNull(checkTeacher)){
            return null;
        }


        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);
        if (existingTeacher == null) return null;

        Teacher teacherUpdate = teacherMapper.toEntity(teacherDTO);

        existingTeacher.setFirstName(teacherUpdate.getFirstName());
        existingTeacher.setLastName(teacherUpdate.getLastName());
        existingTeacher.setSubject(teacherUpdate.getSubject());

        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return teacherMapper.toDto(updatedTeacher);
    }

    @Override
    public boolean deleteTeacher(Long id) {
        TeacherDTO checkTeacher = getTeacherById(id);
        if(Objects.isNull(checkTeacher)){
            return false;
        }
        teacherRepository.deleteById(id);
        return true;
    }
}