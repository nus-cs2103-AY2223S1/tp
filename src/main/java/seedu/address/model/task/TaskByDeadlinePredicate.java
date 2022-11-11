package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Deadline} matches any of the keywords given.
 */
public class TaskByDeadlinePredicate implements Predicate<Task> {

    // TODO: Change to use a Deadline object instead
    private final List<String> dates;

    public TaskByDeadlinePredicate(List<String> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Task task) {
        return dates.stream()
                .anyMatch(keyword ->
                        Deadline.isValidDeadline(keyword)
                                ? task.getDeadline().compareTo(new Deadline(keyword)) >= 0 : false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskByDeadlinePredicate // instanceof handles nulls
                && dates.equals(((TaskByDeadlinePredicate) other).dates)); // state check
    }

    @Override
    public String toString() {
        assert this.dates.size() == 1 : "There should only be one date";
        return dates.get(0).equals("")
                ? "Tasks with no deadline"
                : "Tasks due on or after '" + dates.get(0) + "'";
    }

}
