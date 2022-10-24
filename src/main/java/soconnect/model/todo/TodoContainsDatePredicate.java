package soconnect.model.todo;

import java.util.function.Predicate;

public class TodoContainsDatePredicate implements Predicate<Todo> {

    private final Date date;

    public TodoContainsDatePredicate(Date date) {
        this.date = date;
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
