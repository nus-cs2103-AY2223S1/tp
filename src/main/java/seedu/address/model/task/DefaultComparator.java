package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.TaskList;

/**
 * Compares two tasks on the basis of the task added later ordered in front of the other task.
 */
public class DefaultComparator implements Comparator<Task> {

    private TaskList taskList;

    /**
     * Constructs a {@code DefaultComparator} with the given tasklist.
     *
     * @param taskList A valid TaskList.
     */
    public DefaultComparator(TaskList taskList) {
        requireNonNull(taskList);
        this.taskList = taskList;
    }

    /**
     * Constructs a DefaultComparator with an empty tasklist.
     */
    public DefaultComparator() {
        this.taskList = new TaskList();
    }

    @Override
    public int compare(Task a, Task b) {
        if (taskList.indexOf(a) > taskList.indexOf(b)) {
            return -1;
        } else if (taskList.indexOf(a) < taskList.indexOf(b)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof DefaultComparator) { // instanceof handles nulls
            DefaultComparator otherDefaultComparator = (DefaultComparator) other;
            return this.taskList.equals(otherDefaultComparator.taskList); // state check
        }
        return false;
    }

}
