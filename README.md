# LearnTrack

LearnTrack is a simple console-based Student and Course Management System built with Core Java.

The app lets an admin manage:

- Students
- Courses
- Enrollments

All data is stored in memory using `ArrayList`, so the records reset when the program stops.

## Requirements

- JDK 26.0.1 or a compatible JDK
- Gradle wrapper included in this project

## How to Build

```bash
./gradlew build
```

## How to Run

```bash
./gradlew run
```

## Main Features

- Add, view, search, update, and deactivate students
- Add, view, search, activate, and deactivate courses
- Enroll active students in active courses
- View enrollments for a student
- Mark enrollments as `COMPLETED` or `CANCELLED`
- Handle invalid input without crashing

## Package Structure

```text
com.airtribe.learntrack
├── entity
├── exception
├── service
└── util
```

`Main` handles the console menu. Services contain application logic. Entities hold data.
