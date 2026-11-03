package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        active = true;
    }

    public Student(String id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "not-provided@example.com", batch);
    }

    public Student(String id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " (" + batch + ")";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getDisplayName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", active=" + active +
                '}';
    }
}
