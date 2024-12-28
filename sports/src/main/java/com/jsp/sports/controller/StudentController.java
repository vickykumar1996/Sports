package com.jsp.sports.controller;

import com.jsp.sports.entity.Student;
import com.jsp.sports.payload.StudentDto;
import com.jsp.sports.service.StudentService;
import com.jsp.sports.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> insertData(@RequestBody @Valid StudentDto studentDto) {
        StudentDto studentDto1 = studentService.insertStudent(studentDto);
        return new ResponseEntity<>("Data is inserted successfully", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        StudentDto studentById = studentService.getStudentById(id);
       return new ResponseEntity<>("Get Student by this id " + id + studentById.toString() , HttpStatus.FOUND);
       // not use below
//        if (studentById != null) {
//            return new ResponseEntity<>("Get Student by this id " + id + ": " + studentById.toString(), HttpStatus.FOUND);
//        } else {
//            return new ResponseEntity<>("Student not found with id " + id, HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }
    @GetMapping("/page")
    public ResponseEntity<List<StudentDto>> getAllStudents1(
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false)int pageNo ,
            @RequestParam(value = "pageSize" , defaultValue = "5" , required = false)int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = "id" , required = false)String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = "asc" , required = false)String sortDir


    ) {
        List<StudentDto> dtos = studentService.getAllStudent1(pageNo, pageSize , sortBy , sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
@PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id,@RequestBody StudentDto dto){
    StudentDto updatedStudent = studentService.updateStudent(id, dto);
    return new ResponseEntity<>("Student is update",HttpStatus.OK);

}
@GetMapping("/names")
public ResponseEntity<Student> findByStudentName(@RequestParam String namess){
    Student byName = studentService.findByName(namess);
    return new ResponseEntity<>(byName,HttpStatus.FOUND);
}
}
