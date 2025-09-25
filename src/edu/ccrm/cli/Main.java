package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.BackupServiceImpl;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.io.ImportExportServiceImpl;
import edu.ccrm.service.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService = new StudentServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final EnrollmentService enrollmentService = new EnrollmentServiceImpl(courseService);
    private static final ImportExportService ioService =
        new ImportExportServiceImpl(studentService, courseService, enrollmentService);
    private static final BackupService backupService = new BackupServiceImpl();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getInt("Enter choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> listStudents();
                case 3 -> addCourse();
                case 4 -> listCourses();
                case 5 -> enrollStudentInCourse();
                case 6 -> recordMarks();
                case 7 -> showTranscript();
                case 8 -> ioService.exportData();
                case 9 -> ioService.importData();
                case 10 -> backupService.backupData();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("Exiting... Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n==== Campus Course & Records Manager ====");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Add Course");
        System.out.println("4. List Courses");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. Record Marks for Enrollment");
        System.out.println("7. Show Transcript (GPA)");
        System.out.println("8. Export Data");
        System.out.println("9. Import Data");
        System.out.println("10. Backup Data");
        System.out.println("0. Exit");
    }

    // ---------- Menu Operations ----------
    private static void addStudent() {
        System.out.print("Enter Registration No: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Student s = new Student(regNo, name, email);
        studentService.addStudent(s);

        System.out.println("Student added successfully!");
    }

    private static void listStudents() {
        System.out.println("\n-- All Students --");
        studentService.listStudents().forEach(System.out::println);
    }

    private static void addCourse() {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        int credits = getInt("Enter Credits: ");
        System.out.print("Enter Instructor Name: ");
        String instrName = scanner.nextLine();
        System.out.print("Enter Instructor Email: ");
        String instrEmail = scanner.nextLine();
        System.out.print("Enter Instructor Department: ");
        String dept = scanner.nextLine();

        Instructor instructor = new Instructor(instrName, instrEmail, dept);

        System.out.println("Choose Semester (1=SPRING, 2=SUMMER, 3=FALL): ");
        int semChoice = getInt("");
        Semester sem = switch (semChoice) {
            case 1 -> Semester.SPRING;
            case 2 -> Semester.SUMMER;
            case 3 -> Semester.FALL;
            default -> Semester.SPRING;
        };

        Course c = new Course.Builder()
        .code(code)
        .title(title)
        .credits(credits)
        .instructor(instructor)
        .semester(sem)
        .department(dept)
        .build();
        courseService.addCourse(c);

        System.out.println("Course added successfully!");
    }

    private static void listCourses() {
        System.out.println("\n-- All Courses --");
        courseService.listCourses().forEach(System.out::println);
    }

    private static void enrollStudentInCourse() {
    System.out.print("Enter Student RegNo: ");
    String regNo = scanner.nextLine();
    Student s = studentService.findByRegNo(regNo);
    if (s == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.print("Enter Course Code: ");
    String courseCode = scanner.nextLine();
    Course c = courseService.findByCode(courseCode);
    if (c == null) {
        System.out.println("Course not found.");
        return;
    }

    enrollmentService.enrollStudent(s.getId(), courseCode);
    System.out.println("Enrolled successfully!");
}

private static void recordMarks() {
    System.out.print("Enter Student RegNo: ");
    String regNo = scanner.nextLine();
    Student s = studentService.findByRegNo(regNo);
    if (s == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.print("Enter Course Code: ");
    String courseCode = scanner.nextLine();
    int marks = getInt("Enter Marks (0-100): ");
    enrollmentService.recordMarks(s.getId(), courseCode, marks);

    System.out.println("Marks recorded!");
}

private static void showTranscript() {
    System.out.print("Enter Student RegNo: ");
    String regNo = scanner.nextLine();
    Student s = studentService.findByRegNo(regNo);
    if (s == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println("\n--- Transcript for " + s.getFullName() + " ---");
    var enrollments = enrollmentService.getEnrollmentsForStudent(s.getId());
    for (Enrollment e : enrollments) {
        Course c = courseService.findByCode(e.getCourseCode());
        System.out.println(c.getTitle() + " (" + c.getCode() + ") "
                + "Marks=" + e.getMarks() + ", Grade=" + e.getGrade());
    }

    double gpa = enrollmentService.calculateGPA(s.getId());
    System.out.printf("GPA: %.2f%n", gpa);
}

    // ---------- Helpers ----------
    private static int getInt(String prompt) {
        while (true) {
            try {
                if (!prompt.isBlank()) {
                    System.out.print(prompt);
                }
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}