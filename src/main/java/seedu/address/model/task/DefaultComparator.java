package seedu.address.model.task;

import java.util.Comparator;

import seedu.address.model.TaskList;

/**
 * Compares two tasks on the basis of the task added later order in front of the other task.
 */
public class DefaultComparator implements Comparator<Task> {

    private TaskList taskList;

    public DefaultComparator(TaskList taskList) {
        this.taskList = taskList;
    }

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

}
