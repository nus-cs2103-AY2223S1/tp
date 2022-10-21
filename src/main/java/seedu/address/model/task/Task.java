package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.student.Student;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final TaskName taskName;

    // Data fields
    private final TaskDescription taskDescription;
    private final TaskDeadline taskDeadline;
    private final Set<Student> students = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, TaskDescription taskDescription, TaskDeadline taskDeadline, Set<Student> students) {
        requireAllNonNull(taskName, taskDescription, taskDeadline, students);
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        this.students.addAll(students);
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public TaskDeadline getTaskDeadline() {
        return taskDeadline;
    }

    /**
     * Returns an immutable Student set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    /**
     * Returns true if both tasks of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskName().equals(getTaskName());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTaskName().equals(getTaskName())
                && otherTask.getTaskDescription().equals(getTaskDescription())
                && otherTask.getTaskDeadline().equals(getTaskDeadline())
                && otherTask.getStudents().equals(getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskDescription, taskDeadline, students);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskName())
                .append(" Task Description: ")
                .append(getTaskDescription())
                .append(" Task Deadline: ")
                .append(getTaskDeadline().toString())
                .append(" Students: ");

        Set<Student> students = getStudents();
        if (students.isEmpty()) {
            builder.append("None");
        } else {
            students.forEach(builder::append);
        }
        return builder.toString();
    }

}
