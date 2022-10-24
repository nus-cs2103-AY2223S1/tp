package seedu.address.model.task;

import java.util.Comparator;

public class SortByDeadlineComparator implements Comparator<Task> {

    @Override
    public int compare(Task a, Task b) {
        if (a.getDeadline().deadline.isBefore(b.getDeadline().deadline)) {
            return -1;
        } else if (a.getDeadline().deadline.isAfter(b.getDeadline().deadline)) {
            return 1;
        }
        return 0;
    }

}
