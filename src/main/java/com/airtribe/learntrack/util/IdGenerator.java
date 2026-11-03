package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentCounter = 1000;
    private static int courseCounter = 1000;
    private static int enrollmentCounter = 1000;

    private IdGenerator() {
    }

    public static String generateStudentId() {
        studentCounter++;
        return "STD" + studentCounter;
    }

    public static String generateCourseId() {
        courseCounter++;
        return "COU" + courseCounter;
    }

    public static String generateEnrollmentId() {
        enrollmentCounter++;
        return "ENR" + enrollmentCounter;
    }
}
