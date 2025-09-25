package edu.ccrm.service;

import edu.ccrm.domain.Status;
import edu.ccrm.domain.Student;

import java.util.*;

public class StudentServiceImpl implements StudentService {
    private final Map<UUID, Student> students = new HashMap<>();

    @Override
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student findById(UUID id) {
        return students.get(id);
    }

    @Override
    public Student findByRegNo(String regNo) {
        return students.values()
                .stream()
                .filter(s -> s.getRegNo().equalsIgnoreCase(regNo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateStudent(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public void deactivateStudent(UUID id) {
        Student s = students.get(id);
        if (s != null) {
            s.setStatus(Status.INACTIVE);
        }
    }
}