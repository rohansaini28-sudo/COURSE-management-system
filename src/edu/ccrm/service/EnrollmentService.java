package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
// import edu.ccrm.domain.Grade;
import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    void enrollStudent(UUID studentId, String courseCode);
    void recordMarks(UUID studentId, String courseCode, int marks);
    List<Enrollment> getEnrollmentsForStudent(UUID studentId);
    double calculateGPA(UUID studentId);
}

