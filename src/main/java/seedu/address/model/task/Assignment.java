package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.List;


/**
 * Represents an Assignment in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment extends Task {
    private final List<String> students;

    /**
     * Every field must be present and not null.
     */
    public Assignment(TaskTitle title, TaskDescription desc, List<String> students) {
        super(title, desc);
        requireNonNull(students);
        this.students = students;
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

        builder.append(super.toString())
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

        return super.equals(otherStudent)
                && otherStudent.getStudents().equals(getStudents());
    }
}
