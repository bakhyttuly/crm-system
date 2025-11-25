package com.example.crmsystem.service;

import com.example.crmsystem.dto.StudentDTO;
import com.example.crmsystem.entity.Student;
import com.example.crmsystem.entity.Teacher;
import com.example.crmsystem.repository.StudentRepository;
import com.example.crmsystem.repository.TeacherRepository;
import com.example.crmsystem.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final TeacherRepository teacherRepository;

    private void setTeacherRelationships(Student student, List<Long> teacherIds) {
        if (teacherIds != null && !teacherIds.isEmpty()) {

            List<Teacher> teachers = teacherRepository.findAllById(teacherIds);


            Set<Teacher> teacherSet = new HashSet<>(teachers);
            student.setTeachers(teacherSet);

        } else {
            student.setTeachers(new HashSet<>());
        }
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);

        setTeacherRelationships(student, studentDTO.getTeacherIds());

        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDtoList(students);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(Objects.isNull(student)){
            return null;
        }
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        StudentDTO checkStudent = getStudentById(id);
        if(Objects.isNull(checkStudent)){
            return null;
        }

        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) return null;


        Student studentToUpdate = studentMapper.toEntity(studentDTO);

        existingStudent.setFirstName(studentToUpdate.getFirstName());
        existingStudent.setLastName(studentToUpdate.getLastName());
        existingStudent.setEmail(studentToUpdate.getEmail());
        existingStudent.setMajor(studentToUpdate.getMajor());
        existingStudent.setSemester(studentToUpdate.getSemester());
        existingStudent.setGpa(studentToUpdate.getGpa());

        existingStudent.setCourse(studentToUpdate.getCourse());


        setTeacherRelationships(existingStudent, studentDTO.getTeacherIds());

        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toDto(updatedStudent);
    }

    @Override
    public boolean deleteStudent(Long id) {
        StudentDTO checkStudent = getStudentById(id);
        if(Objects.isNull(checkStudent)){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
}