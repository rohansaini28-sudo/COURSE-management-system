package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Enrollment {
    private UUID studentId;
    private String courseCode;
    private LocalDate enrolledOn;
    private Integer marks; // can be null until grading
    private Grade grade;   // can be null until grading

    public Enrollment(UUID studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrolledOn = LocalDate.now();
    }

    // Getters and setters
    public UUID getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public LocalDate getEnrolledOn() {
        return enrolledOn;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", courseCode='" + courseCode + '\'' +
                ", enrolledOn=" + enrolledOn +
                ", marks=" + marks +
                ", grade=" + grade +
                '}';
    }
}