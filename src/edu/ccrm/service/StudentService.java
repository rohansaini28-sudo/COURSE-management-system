package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    void addStudent(Student student);
    List<Student> listStudents();
    Student findById(UUID id);
    Student findByRegNo(String regNo);
    void updateStudent(Student student);
    void deactivateStudent(UUID id);
}