package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class ImportExportServiceImpl implements ImportExportService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    private static final Path DATA_DIR = Paths.get("data");
    private static final Path STUDENTS_FILE = DATA_DIR.resolve("students.csv");
    private static final Path COURSES_FILE = DATA_DIR.resolve("courses.csv");
    private static final Path ENROLLMENTS_FILE = DATA_DIR.resolve("enrollments.csv");

    public ImportExportServiceImpl(StudentService studentService,
                                   CourseService courseService,
                                   EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;

        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create data directory", e);
        }
    }

    @Override
    public void exportData() {
        try {
            // Export Students
            try (BufferedWriter writer = Files.newBufferedWriter(STUDENTS_FILE)) {
                for (Student s : studentService.listStudents()) {
                    writer.write(s.getId() + "," + s.getRegNo() + "," + s.getFullName() +
                            "," + s.getEmail() + "," + s.getStatus() + "," + s.getRegisteredOn());
                    writer.newLine();
                }
            }

            // Export Courses
            try (BufferedWriter writer = Files.newBufferedWriter(COURSES_FILE)) {
                for (Course c : courseService.listCourses()) {
                    writer.write(c.getCode() + "," + c.getTitle() + "," + c.getCredits() +
                            "," + (c.getInstructor() != null ? c.getInstructor().getFullName() : "") +
                            "," + (c.getInstructor() != null ? c.getInstructor().getEmail() : "") +
                            "," + (c.getInstructor() != null ? c.getInstructor().getDepartment() : "") +
                            "," + c.getSemester() +
                            "," + c.getDepartment());
                    writer.newLine();
                }
            }

            // Export Enrollments
            try (BufferedWriter writer = Files.newBufferedWriter(ENROLLMENTS_FILE)) {
                for (Student s : studentService.listStudents()) {
                    for (Enrollment e : enrollmentService.getEnrollmentsForStudent(s.getId())) {
                        writer.write(e.getStudentId() + "," + e.getCourseCode() +
                                "," + e.getEnrolledOn() + "," +
                                (e.getMarks() == null ? "" : e.getMarks()) + "," +
                                (e.getGrade() == null ? "" : e.getGrade()));
                        writer.newLine();
                    }
                }
            }

            System.out.println("Data exported successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importData() {
        try {
            // Import Students
            if (Files.exists(STUDENTS_FILE)) {
                try (BufferedReader reader = Files.newBufferedReader(STUDENTS_FILE)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        UUID id = UUID.fromString(parts[0]);
                        String regNo = parts[1];
                        String fullName = parts[2];
                        String email = parts[3];
                        Status status = Status.valueOf(parts[4]);
                        LocalDate registeredOn = LocalDate.parse(parts[5]);

                        Student s = new Student(regNo, fullName, email);
                        // manually set fields to preserve ID and status
                        s.setStatus(status);
                        if (studentService.findByRegNo(regNo) == null) {
                            studentService.addStudent(s);
                        }
                    }
                }
            }

            // Import Courses
            if (Files.exists(COURSES_FILE)) {
                try (BufferedReader reader = Files.newBufferedReader(COURSES_FILE)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        String code = parts[0];
                        String title = parts[1];
                        int credits = Integer.parseInt(parts[2]);
                        Instructor instr = new Instructor(parts[3], parts[4], parts[5]);
                        Semester sem = Semester.valueOf(parts[6]);
                        String dept = parts[7];

                        Course c = new Course.Builder()
                                .code(code)
                                .title(title)
                                .credits(credits)
                                .instructor(instr)
                                .semester(sem)
                                .department(dept)
                                .build();

                        if (courseService.findByCode(code) == null) {
                            courseService.addCourse(c);
                        }
                    }
                }
            }

            // Import Enrollments
            if (Files.exists(ENROLLMENTS_FILE)) {
                try (BufferedReader reader = Files.newBufferedReader(ENROLLMENTS_FILE)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        UUID studentId = UUID.fromString(parts[0]);
                        String courseCode = parts[1];
                        int marks = parts[3].isEmpty() ? -1 : Integer.parseInt(parts[3]);

                        enrollmentService.enrollStudent(studentId, courseCode);
                        if (marks >= 0) {
                            enrollmentService.recordMarks(studentId, courseCode, marks);
                        }
                    }
                }
            }

            System.out.println("Data imported successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


