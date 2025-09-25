package edu.ccrm.config;

import edu.ccrm.service.*;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    private AppConfig() {
        courseService = new CourseServiceImpl();
        studentService = new StudentServiceImpl();
        enrollmentService = new EnrollmentServiceImpl(courseService);
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }
}