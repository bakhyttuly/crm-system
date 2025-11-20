package com.example.crmsystem.service;

import com.example.crmsystem.dto.StudentDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StudentService {

     StudentDTO createStudent(StudentDTO studentDTO);
     List<StudentDTO> getAllStudents();

     StudentDTO getStudentById(Long id);

     StudentDTO updateStudent(Long id, StudentDTO studentDTO);

     boolean deleteStudent(Long id);
}
