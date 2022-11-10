package seedu.address.model.task;

import java.util.Comparator;

/**
 * Helper class implementing Comparator interface to sort a task list based on their respective statuses.
 */
public class SortByStatus implements Comparator<Task> {

    /**
     * Compares two Tasks based on their statuses for order. Returns a negative integer, zero, or a positive integer
     * as the status of the first Task is less than, equal to, or greater than the second.
     * @param task1 the first Task to be compared
     * @param task2 the second Task to be compared
     * @return a negative integer, zero, or a positive integer as the status of the first Task is less than,
     *        equal to, or greater than the second
     */
    public int compare(Task task1, Task task2) {
        if (!task1.isDone() && task2.isDone()) {
            return -1;
        }

        if (task1.isDone() && !task2.isDone()) {
            return 1;
        }

        return 0;
    }
}
