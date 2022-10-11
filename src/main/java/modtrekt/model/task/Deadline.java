package modtrekt.model.task;

import java.time.LocalDate;

import modtrekt.model.module.ModCode;

/**
 * Represents a deadline in the task list.
 * Similar to task, but accepts a due date.
 */
public class Deadline extends Task {
    private final LocalDate dueDate;

    /**
     * Constructor for deadline object.
     *
     * @param description description of task
     * @param module module code of task
     * @param dueDate date object representing due date
     */
    public Deadline(Description description, ModCode module, LocalDate dueDate, boolean isArchived) {
        super(description, module, isArchived);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for deadline objects, with a default unarchived state.
     *
     * @param description description of task
     * @param module module code of task
     * @param dueDate date object representing due date
     */
    public Deadline(Description description, ModCode module, LocalDate dueDate) {
        super(description, module);
        this.dueDate = dueDate;
    }

    /**
     * Gets due date of deadline.
     * @return Due date of deadline
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (super.equals(other) && other instanceof Deadline) {
            return ((Deadline) other).getDueDate().isEqual(dueDate);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s due %s", super.toString(), dueDate);
    }
}
