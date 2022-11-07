package modtrekt.model.util;

import java.util.Comparator;

import modtrekt.model.task.Deadline;
import modtrekt.model.task.Task;

/**
 * Comparator that compares tasks based on
 * 1) Whether they are a deadline or not, deadlines are considered greater
 * 2) If they are a deadline, which deadline's due date is sooner.
 */
public class DeadlineComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Deadline && !(t2 instanceof Deadline)) {
            return 1;
        } else if (!(t1 instanceof Deadline) && t2 instanceof Deadline) {
            return -1;
        } else if (t1 instanceof Deadline && t2 instanceof Deadline) {
            // Both deadlines, multiply by -1 since default compareTo method puts earlier date as greater
            return -1 * ((Deadline) t1).getDueDate().compareTo(((Deadline) t2).getDueDate());
        } else {
            // Both tasks
            return 0;
        }
    }
}
