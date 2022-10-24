package soconnect.model.todo;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Todo} is of a certain {@code Priority} and
 * the {@code Date} of the {@code Todo} should not be earlier than the current date.
 */
public class TodoContainsPriorityPredicate implements Predicate<Todo> {

    private final Priority priority;

    public TodoContainsPriorityPredicate(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean test(Todo todo) {
        Date now = new Date(LocalDate.now());

        return !todo.isBefore(now) && todo.getPriority().equals(priority);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoContainsPriorityPredicate // instanceof handles nulls
            && priority.equals(((TodoContainsPriorityPredicate) other).priority)); // state check
    }
}
