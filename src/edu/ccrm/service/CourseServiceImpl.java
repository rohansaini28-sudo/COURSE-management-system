package edu.ccrm.service;

import edu.ccrm.domain.Course;

import java.util.*;

public class CourseServiceImpl implements CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    @Override
    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course findByCode(String code) {
        return courses.get(code);
    }

    public List<Course> findByDepartment(String dept) {
        return courses.values().stream()
            .filter(c -> c.getDepartment().equalsIgnoreCase(dept))
            .toList();
}

    public List<Course> sortByCredits() {
        return courses.values().stream()
            .sorted(Comparator.comparingInt(Course::getCredits))
            .toList();
    }
}