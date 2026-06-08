package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final List<Enrollment> enrollments;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(String studentId, String courseId) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student.");
        }

        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course.");
        }

        Enrollment enrollment = new Enrollment(IdGenerator.generateEnrollmentId(), studentId, courseId);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(String studentId) {
        studentService.findStudentById(studentId);
        List<Enrollment> studentEnrollments = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equalsIgnoreCase(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }

        return studentEnrollments;
    }

    public Enrollment updateEnrollmentStatus(String enrollmentId, String status) {
        if (!status.equals("COMPLETED") && !status.equals("CANCELLED")) {
            throw new InvalidInputException("Status must be COMPLETED or CANCELLED.");
        }

        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(status);
        return enrollment;
    }

    private Enrollment findEnrollmentById(String enrollmentId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equalsIgnoreCase(enrollmentId)) {
                return enrollment;
            }
        }

        throw new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId);
    }
}
