package com.example.crmsystem.service;

import com.example.crmsystem.dto.StudentDTO;
import com.example.crmsystem.entity.Student;
import com.example.crmsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO toDto(Student student) {
        if (student == null) return null;
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setMajor(student.getMajor());
        dto.setSemester(student.getSemester());
        dto.setGpa(student.getGpa());
        return dto;
    }

    @Override
    public Student toEntity(StudentDTO dto) {
        if (dto == null) return null;
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setMajor(dto.getMajor());
        student.setSemester(dto.getSemester());
        student.setGpa(dto.getGpa());
        return student;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return toDto(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(toDto(student));
        }
        return dtos;
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return toDto(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setMajor(studentDTO.getMajor());
        existingStudent.setSemester(studentDTO.getSemester());
        existingStudent.setGpa(studentDTO.getGpa());

        Student updatedStudent = studentRepository.save(existingStudent);
        return toDto(updatedStudent);
    }

    @Override
    public boolean deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        studentRepository.delete(student);
        return true;
    }
}
