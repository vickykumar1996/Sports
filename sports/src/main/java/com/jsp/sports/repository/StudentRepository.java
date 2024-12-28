package com.jsp.sports.repository;

import com.jsp.sports.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
   @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
   Optional<Student> findByName(@Param("name") String name);
}
