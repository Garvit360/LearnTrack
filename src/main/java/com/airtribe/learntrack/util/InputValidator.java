package com.airtribe.learntrack.util;

public class InputValidator {
    private InputValidator() {
    }

    public static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return hasText(email) && email.contains("@") && email.contains(".");
    }
}
