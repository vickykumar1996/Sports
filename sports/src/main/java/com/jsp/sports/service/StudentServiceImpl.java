package com.jsp.sports.service;

import com.jsp.sports.entity.Student;
import com.jsp.sports.payload.StudentDto;
import com.jsp.sports.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto insertStudent(StudentDto studentDto) {
        Student convertIntoEntity = modelMapper.map(studentDto, Student.class);
        Student saveStudent = studentRepository.save(convertIntoEntity);
        return modelMapper.map(saveStudent,StudentDto.class);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(()->new RuntimeException("id is not present" + id));
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> allStudent = studentRepository.findAll();
        return allStudent.stream().map(e->modelMapper.map(e,StudentDto.class))
                .collect(Collectors.toList());
    }
    public List<StudentDto> getAllStudent1(int pageNo , int pageSize ,String sortBy , String sortDir) {
      Sort sort =  sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
       Sort.by(sortBy).descending() ;

        Pageable pageable =  PageRequest.of(pageNo , pageSize , sort);
        Page<Student> allStudent = studentRepository.findAll(pageable);
        return allStudent.stream().map(e->modelMapper.map(e,StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto student) {
        Student student1 = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("id is not present"));
            student1.setName(student.getName());
            student1.setCourse(student.getCourse());
            student1.setEmail(student.getEmail());
            studentRepository.save(student1);
            return modelMapper.map(student1,StudentDto.class);
        }

    @Override
    public Student findByName(String names) {
        Student studentByName = studentRepository.findByName(names).orElseThrow(()->new RuntimeException("name is not present"));
        return studentByName;

    }
}



