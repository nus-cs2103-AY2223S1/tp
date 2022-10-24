package soconnect.model.todo;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Todo} is of a certain {@code Date}.
 */
public class TodoContainsDatePredicate implements Predicate<Todo> {

    private final Date date;

    public TodoContainsDatePredicate(Date date) {
        this.date = date;
    }

    /**
     * Creates an instance of {@code TodoContainsDatePredicate} that tests
     * the {@code Todo} is of the current date.
     *
     * @return A {@code TodoContainsDatePredicate} instance that tests the {@code Todo} is of the current date.
     */
    public static TodoContainsDatePredicate currentDate() {
        Date today = new Date(LocalDate.now());
        return new TodoContainsDatePredicate(today);
    }

    @Override
    public boolean test(Todo todo) {
        return todo.getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoContainsDatePredicate // instanceof handles nulls
                && date.equals(((TodoContainsDatePredicate) other).date)); // state check
    }
}
