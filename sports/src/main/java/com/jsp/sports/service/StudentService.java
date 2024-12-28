package com.jsp.sports.service;

import com.jsp.sports.entity.Student;
import com.jsp.sports.payload.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

   StudentDto insertStudent(StudentDto studentDtodto);

  StudentDto getStudentById(Long id);
  List<StudentDto> getAllStudent();

 StudentDto updateStudent(Long id,StudentDto student);
    Student findByName(String names);


}
