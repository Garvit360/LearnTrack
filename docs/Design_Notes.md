# Design Notes

## Why ArrayList Was Used

`ArrayList` was used because the number of students, courses, and enrollments can grow while the program is running. A normal array has a fixed size, but an `ArrayList` can grow dynamically and is easier to use for adding and listing records.

## Where Static Members Were Used

Static counters and methods are used in `IdGenerator`. The app needs one shared counter for student IDs, course IDs, and enrollment IDs. Static members are useful here because ID generation belongs to the utility class, not to one object.

Example IDs:

- `STD1001`
- `COU1001`
- `ENR1001`

## Where Inheritance Was Used

`Student` extends `Person`. Common fields like `id`, `firstName`, `lastName`, and `email` are stored in `Person`, while student-specific fields like `batch` and `active` are stored in `Student`.

`Student` also overrides `getDisplayName()` to include the batch. This demonstrates basic polymorphism in a simple way.

## Separation of Concerns

The project is split into small packages:

- `entity`: data classes such as `Student`, `Course`, and `Enrollment`
- `service`: application logic for managing records
- `exception`: custom exceptions
- `util`: helper classes

The `Main` class focuses on showing menus, reading input, and calling service methods.
