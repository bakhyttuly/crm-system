package com.example.crmsystem.service;

import com.example.crmsystem.dto.StudentDTO;
import com.example.crmsystem.entity.Student;
import com.example.crmsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public StudentDTO toDto(Student student) {
        if (student == null) {
            return null;
        }
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


    public Student toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
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

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return toDto(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return toDto(student);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setMajor(studentDTO.getMajor());
        existingStudent.setSemester(studentDTO.getSemester());
        existingStudent.setGpa(studentDTO.getGpa());

        Student updatedStudent = studentRepository.save(existingStudent);
        return toDto(updatedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
