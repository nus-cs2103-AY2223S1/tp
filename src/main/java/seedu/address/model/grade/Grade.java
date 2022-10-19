package seedu.address.model.grade;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.student.Student;
import seedu.address.model.task.Task;

/**
 * Represents a Grade, which is a relationship between a Task and a Student.
 * Guarantees: details are present and not null, field values are validated and mutable.
 */
public class Grade {

    // Identity fields
    private final Student student;
    private final Task task;
    // Data field
    private boolean isGraded = false;

    /**
     * Constructs a Grade object which represents the state of not being graded by default.
     * @param student student who is graded, must not be null
     * @param task task that is graded, must not be null
     */
    public Grade(Student student, Task task) {
        requireAllNonNull(student, task);
        this.student = student;
        this.task = task;
    }

    /**
     * Constructs a Grade object.
     * @param student student who is graded, must not be null
     * @param task task that is graded, must not be null
     * @param isGraded whether the task is graded or not
     */
    public Grade(Student student, Task task, boolean isGraded) {
        requireAllNonNull(student, task);
        this.student = student;
        this.task = task;
        this.isGraded = isGraded;
    }

    /**
     * Changes the grade status to graded
     */
    public void setAsGraded() {
        this.isGraded = true;
    }

    /**
     * Changes the grade status to not graded
     */
    public void setAsNotGraded() {
        this.isGraded = false;
    }

    /**
     * Get the grade status of the task
     * @return true if the task is graded for the student, false otherwise
     */
    public boolean isGraded() {
        return isGraded;
    }
}
