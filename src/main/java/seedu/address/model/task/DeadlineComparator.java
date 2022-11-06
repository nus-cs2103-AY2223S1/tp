package seedu.address.model.task;

import java.util.Comparator;

/**
 * Compares two tasks on the basis of the task with an earlier deadline ordered in front of the other task.
 */
public class DeadlineComparator implements Comparator<Task> {

    private static final String COMPARE_CRITERIA = "in order of task with earliest deadline.";

    @Override
    public int compare(Task a, Task b) {
        if (a.getDeadline().deadline.isBefore(b.getDeadline().deadline)) {
            return -1;
        } else if (a.getDeadline().deadline.isAfter(b.getDeadline().deadline)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof DeadlineComparator) { // instanceof handles nulls
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return COMPARE_CRITERIA;
    }

}
