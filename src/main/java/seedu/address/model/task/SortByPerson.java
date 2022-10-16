package seedu.address.model.task;

import java.util.Comparator;

/**
 * Helper class implementing Comparator interface to sort a task list based on the people they are assigned to.
 */
public class SortByPerson implements Comparator<Task> {

    /**
     * Compares two Tasks based on their assigned Person for order. Returns a negative integer, zero, or a positive
     * integer as the Person of the first Task is less than, equal to, or greater than the second.
     * @param task1 the first Task to be compared
     * @param task2 the second Task to be compared
     * @return a negative integer, zero, or a positive integer as the Person of the first Task is less than,
     *         equal to, or greater than the second
     */
    public int compare(Task task1, Task task2) {
        if (task1.getPersonEmailAddress().equals(task2.getPersonEmailAddress())) {
            return 0;
        }

        return task1.getPersonEmailAddress().toString().compareTo(task2.getPersonEmailAddress().toString());
    }
}
