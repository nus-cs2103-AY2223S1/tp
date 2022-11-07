package seedu.address.model.grade;

import java.util.Objects;

import seedu.address.model.student.Student;
import seedu.address.model.task.Task;


/**
 * Represents the key to a particular Grade.
 * Adapted from <a href="https://stackoverflow.com/a/26981910">Stack Overflow:How to create a HashMap with two keys</a>
 */
public class GradeKey {
    public final Student student;
    public final Task task;

    /**
     * Constructor for the key to a grade.
     * @param student the student whose grade needs to be retrieved
     * @param task associated with the grade
     */
    public GradeKey(final Student student, final Task task) {
        this.student = student;
        this.task = task;
    }

    /**
     * Checks if two keys to the Grade are equal.
     * @param otherGradeKey the other Grade key
     * @return true if the gradeKey and the task are equal, false otherwise
     */
    @Override
    public boolean equals(final Object otherGradeKey) {
        if (!(otherGradeKey instanceof GradeKey)) {
            return false;
        }
        if (!((GradeKey) otherGradeKey).student.equals(this.student)) {
            return false;
        }
        return ((GradeKey) otherGradeKey).task.equals(this.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.task);
    }

    public Student getStudent() {
        return this.student;
    }

    public Task getTask() {
        return this.task;
    }
}
