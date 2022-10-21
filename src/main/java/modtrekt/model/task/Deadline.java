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
     * Constructor for an instance of Deadline with a priority.
     *
     * @param description description of task
     * @param module      module code of note's module
     * @param isArchived  true if task is completed/archived
     * @param priority    priority of task
     */
    public Deadline(Description description, ModCode module, LocalDate dueDate, boolean isArchived, Priority priority) {
        super(description, module, isArchived, priority);
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
    public Task archive() {
        return new Deadline(this.getDescription(), this.getModule(), this.getDueDate(), true);
    }

    @Override
    public Task unarchive() {
        return new Deadline(this.getDescription(), this.getModule(), this.getDueDate(), false);
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
