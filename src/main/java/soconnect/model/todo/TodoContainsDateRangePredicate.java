package soconnect.model.todo;

import java.util.function.Predicate;

/**
 * Tests that a {@code Todo} is within the given date range (inclusive).
 */
public class TodoContainsDateRangePredicate implements Predicate<Todo> {

    private final Date startDate;
    private final Date endDate;

    public TodoContainsDateRangePredicate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean test(Todo todo) {
        return todo.isWithinDateRange(startDate, endDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoContainsDateRangePredicate // instanceof handles nulls
                && startDate.equals(((Date) other).date) // state check
                && endDate.equals(((Date) other).date));
    }

}
