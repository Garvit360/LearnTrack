package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main() {
        scanner = new Scanner(System.in);
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService(studentService, courseService);
    }

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    showStudentMenu();
                    break;
                case 2:
                    showCourseMenu();
                    break;
                case 3:
                    showEnrollmentMenu();
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using LearnTrack.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("===== LearnTrack =====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Exit");
    }

    private void showStudentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("--- Student Management ---");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Deactivate student");
            System.out.println("6. Back");

            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        printStudents(studentService.getAllStudents());
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deactivateStudent();
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void addStudent() {
        String firstName = readText("First name: ");
        String lastName = readText("Last name: ");
        String email = readText("Email: ");
        String batch = readText("Batch: ");

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added with ID: " + student.getId());
    }

    private void searchStudent() {
        String id = readText("Student ID: ");
        Student student = studentService.findStudentById(id);
        System.out.println(student);
    }

    private void updateStudent() {
        String id = readText("Student ID to update: ");
        String firstName = readText("New first name: ");
        String lastName = readText("New last name: ");
        String email = readText("New email: ");
        String batch = readText("New batch: ");

        Student student = studentService.updateStudent(id, firstName, lastName, email, batch);
        System.out.println("Student updated: " + student);
    }

    private void deactivateStudent() {
        String id = readText("Student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void showCourseMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("--- Course Management ---");
            System.out.println("1. Add course");
            System.out.println("2. View all courses");
            System.out.println("3. Search course by ID");
            System.out.println("4. Activate course");
            System.out.println("5. Deactivate course");
            System.out.println("6. Back");

            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        printCourses(courseService.getAllCourses());
                        break;
                    case 3:
                        searchCourse();
                        break;
                    case 4:
                        activateCourse();
                        break;
                    case 5:
                        deactivateCourse();
                        break;
                    case 6:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void addCourse() {
        String name = readText("Course name: ");
        String description = readText("Description: ");
        int duration = readInt("Duration in weeks: ");

        Course course = courseService.addCourse(name, description, duration);
        System.out.println("Course added with ID: " + course.getId());
    }

    private void searchCourse() {
        String id = readText("Course ID: ");
        Course course = courseService.findCourseById(id);
        System.out.println(course);
    }

    private void activateCourse() {
        String id = readText("Course ID to activate: ");
        courseService.activateCourse(id);
        System.out.println("Course activated.");
    }

    private void deactivateCourse() {
        String id = readText("Course ID to deactivate: ");
        courseService.deactivateCourse(id);
        System.out.println("Course deactivated.");
    }

    private void printCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void showEnrollmentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("--- Enrollment Management ---");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. Mark enrollment as completed");
            System.out.println("4. Mark enrollment as cancelled");
            System.out.println("5. Back");

            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        enrollStudent();
                        break;
                    case 2:
                        viewEnrollmentsForStudent();
                        break;
                    case 3:
                        updateEnrollmentStatus("COMPLETED");
                        break;
                    case 4:
                        updateEnrollmentStatus("CANCELLED");
                        break;
                    case 5:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (EntityNotFoundException | InvalidInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void enrollStudent() {
        String studentId = readText("Student ID: ");
        String courseId = readText("Course ID: ");

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrollment created with ID: " + enrollment.getId());
    }

    private void viewEnrollmentsForStudent() {
        String studentId = readText("Student ID: ");
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private void updateEnrollmentStatus(String status) {
        String enrollmentId = readText("Enrollment ID: ");
        Enrollment enrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, status);
        System.out.println("Enrollment updated: " + enrollment);
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
