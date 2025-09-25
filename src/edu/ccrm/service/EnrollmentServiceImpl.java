package edu.ccrm.service;

import edu.ccrm.domain.*;

import java.util.*;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final Map<UUID, List<Enrollment>> enrollmentMap = new HashMap<>();
    private final CourseService courseService;

    public EnrollmentServiceImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void enrollStudent(UUID studentId, String courseCode) {
       List<Enrollment> enrollments = enrollmentMap.computeIfAbsent(studentId, k -> new ArrayList<>());

    // Prevent duplicate enrollment
        boolean alreadyEnrolled = enrollments.stream()
               .anyMatch(e -> e.getCourseCode().equalsIgnoreCase(courseCode));
        if (alreadyEnrolled) {
            throw new RuntimeException(new edu.ccrm.exceptions.DuplicateEnrollmentException(
                "Student already enrolled in course: " + courseCode));
        }

    // Enforce max 20 credits
        int currentCredits = enrollments.stream()
                .mapToInt(e -> {
                    Course c = courseService.findByCode(e.getCourseCode());
                    return (c != null) ? c.getCredits() : 0;
                })
                .sum();
        Course newCourse = courseService.findByCode(courseCode);
        if (newCourse != null && currentCredits + newCourse.getCredits() > 20) {
            throw new RuntimeException(new edu.ccrm.exceptions.MaxCreditLimitExceededException(
                  "Max 20 credits exceeded for student."));
        }

        enrollments.add(new Enrollment(studentId, courseCode));
    }

    @Override
    public void recordMarks(UUID studentId, String courseCode, int marks) {
        List<Enrollment> enrollments = enrollmentMap.get(studentId);
        if (enrollments == null) return;

        for (Enrollment e : enrollments) {
            if (e.getCourseCode().equalsIgnoreCase(courseCode)) {
                e.setMarks(marks);
                e.setGrade(marksToGrade(marks));
            }
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsForStudent(UUID studentId) {
        return enrollmentMap.getOrDefault(studentId, new ArrayList<>());
    }

    @Override
    public double calculateGPA(UUID studentId) {
        List<Enrollment> enrollments = enrollmentMap.get(studentId);
        if (enrollments == null || enrollments.isEmpty()) return 0.0;

        double totalPoints = 0;
        double totalCredits = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                Course c = courseService.findByCode(e.getCourseCode());
                if (c != null) {
                    totalCredits += c.getCredits();
                    totalPoints += c.getCredits() * e.getGrade().getPoints();
                }
            }
        }

        return (totalCredits == 0) ? 0.0 : totalPoints / totalCredits;
    }


    // ---------- Helper ----------
    private Grade marksToGrade(int marks) {
        if (marks >= 90) return Grade.S;
        else if (marks >= 80) return Grade.A;
        else if (marks >= 70) return Grade.B;
        else if (marks >= 60) return Grade.C;
        else if (marks >= 50) return Grade.D;
        else if (marks >= 40) return Grade.E;
        else return Grade.F;
    }
}