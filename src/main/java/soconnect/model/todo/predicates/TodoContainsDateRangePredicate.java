package soconnect.model.todo.predicates;

import java.util.function.Predicate;

import soconnect.model.todo.Date;
import soconnect.model.todo.Todo;

/**
 * Tests that a {@code Todo} is within the given date range.
 */
public class TodoContainsDateRangePredicate implements Predicate<Todo> {

    private final Date startDate;
    private final Date endDate;

    /**
     * Constructs a {@code TodoContainsDateRangePredicate} to test if a {@code Todo}
     * is within the given date range.
     *
     * @param startDate The start date of the date range.
     * @param endDate The end date of the date range.
     */
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
