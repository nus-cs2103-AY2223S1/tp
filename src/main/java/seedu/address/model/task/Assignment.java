package seedu.address.model.task;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Assignment in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment implements Task {
    private final TaskDescription taskDescription;
    private final TaskTitle taskTitle;
    private final List<String> students;

    /**
     * Every field must be present and not null.
     */
    public Assignment(TaskTitle title, TaskDescription desc, List<String> students) {
        requireNonNull(students);
        this.taskTitle = title;
        this.taskDescription = desc;
        this.students = students;
    }
    @Override
    public TaskTitle getTitle() {
        return this.taskTitle;
    }

    @Override
    public TaskDescription getDescription() {
        return this.taskDescription;
    }

    /**
     * Get the List of students assigned to this assignment.
     *
     * @return List of students assigned.
     */
    public List<String> getStudents() {
        return this.students;
    }

    public void addStudent(List<String> studentsToAdd) {
        this.students.addAll(studentsToAdd);
    }

    public void deleteStudent(List<String> studentsToDelete) {
        this.students.removeAll(studentsToDelete);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription())
                .append("; Students: ");
        for (int i = 0; i < students.size(); i++) {
            if (i == students.size() - 1) {
                builder.append(students.get(i));
            } else {
                builder.append(students.get(i) + ", ");
            }
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherStudent = (Assignment) other;

        return otherStudent.getTitle().equals(getTitle())
                && otherStudent.getDescription().equals(getDescription())
                && otherStudent.getStudents().equals(getStudents());
    }
}
