package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        validateStudentInput(firstName, lastName, email, batch);
        Student student = new Student(IdGenerator.generateStudentId(), firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        validateRequired(firstName, "First name");
        validateRequired(lastName, "Last name");
        validateRequired(batch, "Batch");
        Student student = new Student(IdGenerator.generateStudentId(), firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        throw new EntityNotFoundException("Student not found with ID: " + id);
    }

    public Student updateStudent(String id, String firstName, String lastName, String email, String batch) {
        validateStudentInput(firstName, lastName, email, batch);
        Student student = findStudentById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        return student;
    }

    public void deactivateStudent(String id) {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    private void validateStudentInput(String firstName, String lastName, String email, String batch) {
        validateRequired(firstName, "First name");
        validateRequired(lastName, "Last name");
        validateRequired(email, "Email");
        validateRequired(batch, "Batch");

        if (!InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Please enter a valid email address.");
        }
    }

    private void validateRequired(String value, String fieldName) {
        if (!InputValidator.hasText(value)) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }
}
