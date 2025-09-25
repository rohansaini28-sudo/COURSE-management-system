package edu.ccrm.domain;

public class Instructor extends Person {
    private String department;

    public Instructor(String fullName, String email, String department) {
        super(fullName, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "department='" + department + '\'' +
                ", " + super.toString() +
                '}';
    }
}
