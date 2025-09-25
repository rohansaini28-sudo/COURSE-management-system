package edu.ccrm.domain;

public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final Instructor instructor;
    private final Semester semester;
    private final String department;

    // Private constructor 
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
    }

    // ------- Nested Builder Class -------
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;
        private String department;

        public Builder code(String code) { this.code = code; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder credits(int credits) { this.credits = credits; return this; }
        public Builder instructor(Instructor instructor) { this.instructor = instructor; return this; }
        public Builder semester(Semester semester) { this.semester = semester; return this; }
        public Builder department(String department) { this.department = department; return this; }

        public Course build() {
            return new Course(this);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", semester=" + semester +
                ", department='" + department + '\'' +
                ", instructor=" + (instructor != null ? instructor.getFullName() : "N/A") +
                '}';
    }

    // Getters (if needed)
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
}



// package edu.ccrm.domain;    

// public class Course {
//     private String code;
//     private String title;
//     private int credits;
//     private Instructor instructor;
//     private Semester semester;
//     private String department;

//     public Course(String code, String title, int credits, Instructor instructor,
//                   Semester semester, String department) {
//         this.code = code;
//         this.title = title;
//         this.credits = credits;
//         this.instructor = instructor;
//         this.semester = semester;
//         this.department = department;
//     }

//     // Getters and setters
//     public String getCode() {
//         return code;
//     }

//     public void setCode(String code) {
//         this.code = code;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public int getCredits() {
//         return credits;
//     }

//     public void setCredits(int credits) {
//         this.credits = credits;
//     }

//     public Instructor getInstructor() {
//         return instructor;
//     }

//     public void setInstructor(Instructor instructor) {
//         this.instructor = instructor;
//     }

//     public Semester getSemester() {
//         return semester;
//     }

//     public void setSemester(Semester semester) {
//         this.semester = semester;
//     }

//     public String getDepartment() {
//         return department;
//     }

//     public void setDepartment(String department) {
//         this.department = department;
//     }

//     @Override
//     public String toString() {
//         return "Course{" +
//                 "code='" + code + '\'' +
//                 ", title='" + title + '\'' +
//                 ", credits=" + credits +
//                 ", semester=" + semester +
//                 ", department='" + department + '\'' +
//                 ", instructor=" + instructor.getFullName() +
//                 '}';
//     }
// }