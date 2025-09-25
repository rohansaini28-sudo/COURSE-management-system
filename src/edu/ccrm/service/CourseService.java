package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.List;

public interface CourseService {
    void addCourse(Course course);
    List<Course> listCourses();
    Course findByCode(String code);
}