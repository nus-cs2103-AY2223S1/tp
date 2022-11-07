package seedu.codeconnect.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.codeconnect.model.TaskList;

/**
 * Compares two tasks on the basis of the task with an earlier deadline ordered in front of the other task.
 */
public class DeadlineComparator implements Comparator<Task> {

    private static final String COMPARE_CRITERIA = "in order of task with earliest deadline.";

    private TaskList taskList;

    /**
     * Constructs a {@code DefaultComparator} with the given tasklist.
     *
     * @param taskList A valid TaskList.
     */
    public DeadlineComparator(TaskList taskList) {
        requireNonNull(taskList);
        this.taskList = taskList;
    }

    /**
     * Constructs a DefaultComparator with an empty tasklist.
     */
    public DeadlineComparator() {
        this.taskList = new TaskList();
    }

    @Override
    public int compare(Task a, Task b) {
        if (a.getDeadline().deadline.isBefore(b.getDeadline().deadline)) {
            return -1;
        } else if (a.getDeadline().deadline.isAfter(b.getDeadline().deadline)) {
            return 1;
        }
        return new DefaultComparator(taskList).compare(a, b);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof DeadlineComparator) { // instanceof handles nulls
            DeadlineComparator otherDeadlineComparator = (DeadlineComparator) other;
            return this.taskList.equals(otherDeadlineComparator.taskList); // state check
        }
        return false;
    }

    @Override
    public String toString() {
        return COMPARE_CRITERIA;
    }

}
