package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private Status status;
    private List<Enrollment> enrollments;
    private LocalDate registeredOn;

    public Student(String regNo, String fullName, String email) {
        super(fullName, email);
        this.regNo = regNo;
        this.status = Status.ACTIVE;
        this.enrollments = new ArrayList<>();
        this.registeredOn = LocalDate.now();
    }


    // Getters and setters
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    @Override
    public String toString() {
        return "Student{" +
                "regNo='" + regNo + '\'' +
                ", status=" + status +
                ", registeredOn=" + registeredOn +
                ", " + super.toString() +
                '}';
    }
}
