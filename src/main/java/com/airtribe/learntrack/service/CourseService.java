package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses;

    public CourseService() {
        courses = new ArrayList<>();
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        validateCourseInput(courseName, description, durationInWeeks);
        Course course = new Course(IdGenerator.generateCourseId(), courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Course findCourseById(String id) {
        for (Course course : courses) {
            if (course.getId().equalsIgnoreCase(id)) {
                return course;
            }
        }

        throw new EntityNotFoundException("Course not found with ID: " + id);
    }

    public void activateCourse(String id) {
        Course course = findCourseById(id);
        course.setActive(true);
    }

    public void deactivateCourse(String id) {
        Course course = findCourseById(id);
        course.setActive(false);
    }

    private void validateCourseInput(String courseName, String description, int durationInWeeks) {
        if (!InputValidator.hasText(courseName)) {
            throw new InvalidInputException("Course name cannot be empty.");
        }

        if (!InputValidator.hasText(description)) {
            throw new InvalidInputException("Description cannot be empty.");
        }

        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be greater than zero.");
        }
    }
}
